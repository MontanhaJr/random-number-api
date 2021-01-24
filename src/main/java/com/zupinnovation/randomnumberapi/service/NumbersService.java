package com.zupinnovation.randomnumberapi.service;

import com.zupinnovation.randomnumberapi.dto.request.PersonDTO;
import com.zupinnovation.randomnumberapi.dto.request.NumbersDTO;
import com.zupinnovation.randomnumberapi.dto.response.MessageResponseDTO;
import com.zupinnovation.randomnumberapi.entity.Numbers;
import com.zupinnovation.randomnumberapi.entity.Person;
import com.zupinnovation.randomnumberapi.mapper.PersonMapper;
import com.zupinnovation.randomnumberapi.mapper.NumbersMapper;
import com.zupinnovation.randomnumberapi.repository.PersonRepository;
import com.zupinnovation.randomnumberapi.repository.NumbersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class NumbersService {
    private NumbersRepository numbersRepository;
    private PersonRepository personRepository;

    private PersonService personService;

    private PersonMapper personMapper = PersonMapper.INSTANCE;
    private NumbersMapper numbersMapper = NumbersMapper.INSTANCE;

    @Autowired
    public NumbersService(NumbersRepository numbersRepository, PersonRepository personRepository, PersonService personService) {
        this.numbersRepository = numbersRepository;
        this.personRepository = personRepository;
        this.personService = personService;
    }

    public MessageResponseDTO drawNumber(PersonDTO personDTO) {
        Person person = takeEmail(personDTO);

        String numbers = createDrawNumbers();

        NumbersDTO numbersDTO = NumbersDTO.builder()
                .number(numbers)
                .person(person)
                .build();

        createNumber(numbersDTO, numbers);

        personRepository.save(person);

        return MessageResponseDTO.builder().message("The numbers drawn were: " + numbers).build();
    }

    private Person takeEmail(PersonDTO personDTO){
        Person newPerson = personRepository.findByEmail(personDTO.getEmail());

        if (newPerson == null)
        {
            newPerson = personService.createPerson(personDTO);
        }

        return newPerson;
    }

    private String createDrawNumbers() {

        String numbers = "";
        List<Integer> shuffledNumbers = new ArrayList<>();

        for (int i = 1; i < 61; i++) { shuffledNumbers.add(i); }

        Collections.shuffle(shuffledNumbers);

        for (int i = 0; i < 6; i++) {
            numbers = (i != 5) ? numbers + shuffledNumbers.get(i)+"-" : numbers + shuffledNumbers.get(i);
        }

        return numbers;
    }

    private Numbers createNumber(NumbersDTO numbersDTO, String numbersDraw) {
        Numbers numbersToSave = numbersMapper.toModel(numbersDTO);
        numbersToSave.setNumbers(numbersDraw);
        Numbers savedNumbers = numbersRepository.save(numbersToSave);
        return savedNumbers;
    }
}
