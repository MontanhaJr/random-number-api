package com.zupinnovation.randomnumberapi.service;

import com.zupinnovation.randomnumberapi.dto.request.PersonDTO;
import com.zupinnovation.randomnumberapi.dto.request.NumbersDTO;
import com.zupinnovation.randomnumberapi.dto.response.MessageResponseDTO;
import com.zupinnovation.randomnumberapi.entity.Numbers;
import com.zupinnovation.randomnumberapi.entity.Person;
import com.zupinnovation.randomnumberapi.exception.NumberNotFoundException;
import com.zupinnovation.randomnumberapi.mapper.PersonMapper;
import com.zupinnovation.randomnumberapi.mapper.NumbersMapper;
import com.zupinnovation.randomnumberapi.repository.PersonRepository;
import com.zupinnovation.randomnumberapi.repository.NumbersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NumbersService {
    private NumbersRepository numbersRepository;
    private PersonRepository personRepository;

    private PersonMapper personMapper = PersonMapper.INSTANCE;
    private NumbersMapper numbersMapper = NumbersMapper.INSTANCE;

    @Autowired
    public NumbersService(NumbersRepository numbersRepository, PersonRepository personRepository) {
        this.numbersRepository = numbersRepository;
        this.personRepository = personRepository;
    }

    public Numbers drawNumber(PersonDTO personDTO) {
        takeEmail(personDTO);

        insertNumbersDrawn(personDTO);

        Numbers numberToSave = numbersMapper.toModel(randomNumberDTO);

        Numbers savedNumber = numbersRepository.save(numberToSave);
        return savedNumber;
    }

    public Numbers createNumber(String numbers) {
        NumbersDTO numberDTO = NumbersDTO.builder().number(numbers).build();
        Numbers numbersToSave = numbersMapper.toModel(numberDTO);

        Numbers savedNumbers = numbersRepository.save(numbersToSave);
        return savedNumbers;
    }

    public List<NumbersDTO> listAll() {
        List<Numbers> allPeople = numbersRepository.findAll();
        return allPeople.stream()
                .map(numbersMapper::toDTO)
                .collect(Collectors.toList());
    }

//    public PersonDTO findById(Long id) throws PersonNotFoundException {
//        Person person = verifyIfExists(id);
//
//        return personMapper.toDTO(person);
//    }

    public void delete(Long id) throws NumberNotFoundException {
        verifyIfNumberExists(id);
        numbersRepository.deleteById(id);
    }

//    public MessageResponseDTO updateById(PersonDTO personDTO) throws PersonNotFoundException {
//        verifyIfExists(personDTO.getId());
//
//        Person personToUpdate = personMapper.toModel(personDTO);
//        Person updatedPerson = personRepository.save(personToUpdate);
//
//        return MessageResponseDTO.builder().message("Person updated with ID " + updatedPerson.getId()).build();
//    }

    private Numbers verifyIfNumberExists(Long id) throws NumberNotFoundException {
        return numbersRepository.findById(id)
                .orElseThrow(() -> new NumberNotFoundException(id));
    }

    private Person takeEmail(PersonDTO personDTO){
        Person newPerson = personRepository.findByEmail(personDTO.getEmail());

        if (newPerson == null)
        {
            Person personToSave = personMapper.toModel(personDTO);
            newPerson = personRepository.save(personToSave);
        }

        return newPerson;
    }

    public MessageResponseDTO insertNumbersDrawn(PersonDTO personDTO) {
        String numbers = "";
        List<Integer> shuffledNumbers = new ArrayList<>();

        for (int i = 0; i < 61; i++) { shuffledNumbers.add(i); }

        Collections.shuffle(shuffledNumbers);

        for (int i = 0; i < 6; i++) {
            numbers = (i != 5) ? numbers + shuffledNumbers.get(i)+"-" : numbers + shuffledNumbers.get(i);
        }

        Numbers numbersCreated = createNumber(numbers);

        personDTO.setNumbers(numbersCreated);
        Person personToUpdate = personMapper.toModel(personDTO);
        Person updatedPerson = personRepository.save(personToUpdate);

        return MessageResponseDTO.builder().message("Person updated with ID " + updatedPerson.getId()).build();
    }
}
