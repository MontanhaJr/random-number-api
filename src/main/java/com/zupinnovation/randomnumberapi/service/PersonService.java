package com.zupinnovation.randomnumberapi.service;

import com.zupinnovation.randomnumberapi.dto.request.PersonDTO;
import com.zupinnovation.randomnumberapi.entity.Person;
import com.zupinnovation.randomnumberapi.exception.EmailNotFoundException;
import com.zupinnovation.randomnumberapi.mapper.PersonMapper;
import com.zupinnovation.randomnumberapi.repository.NumbersRepository;
import com.zupinnovation.randomnumberapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonService {
    private PersonRepository personRepository;

    private PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
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
