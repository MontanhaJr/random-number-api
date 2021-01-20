package com.zupinnovation.randomnumberapi.controller;

import com.zupinnovation.randomnumberapi.entity.Person;
import com.zupinnovation.randomnumberapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    private PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public String teste() {
        return "Cheguei!";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person createPerson(@RequestBody Person person) {
        Person savedPerson = personRepository.save(person);
        return savedPerson;
    }

}
