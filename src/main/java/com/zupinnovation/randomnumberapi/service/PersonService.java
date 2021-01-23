package com.zupinnovation.randomnumberapi.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zupinnovation.randomnumberapi.dto.request.PersonDTO;
import com.zupinnovation.randomnumberapi.dto.response.MessageResponseDTO;
import com.zupinnovation.randomnumberapi.entity.Numbers;
import com.zupinnovation.randomnumberapi.entity.Person;
import com.zupinnovation.randomnumberapi.exception.EmailNotFoundException;
import com.zupinnovation.randomnumberapi.exception.PersonNotFoundException;
import com.zupinnovation.randomnumberapi.mapper.PersonMapper;
import com.zupinnovation.randomnumberapi.repository.NumbersRepository;
import com.zupinnovation.randomnumberapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private PersonRepository personRepository;
    private NumbersRepository numbersRepository;

    private PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository, NumbersRepository numbersRepository) {
        this.personRepository = personRepository;
        this.numbersRepository = numbersRepository;
    }

    public Person createPerson(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return savedPerson;
    }

    @Transactional
    public List<Person> listAll() {
        List<Person> people = personRepository.findAll();

        return people;
    }

    public Person findByEmail(String email) throws EmailNotFoundException {
        Person person = personRepository.findByEmail(email);

        if (person == null)
        {
            throw new EmailNotFoundException(email);
        }
        return person;
    }
}
