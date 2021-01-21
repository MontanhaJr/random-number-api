package com.zupinnovation.randomnumberapi.controller;

import com.zupinnovation.randomnumberapi.dto.request.PersonDTO;
import com.zupinnovation.randomnumberapi.entity.Person;
import com.zupinnovation.randomnumberapi.repository.PersonRepository;
import com.zupinnovation.randomnumberapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String teste() {
        return "Cheguei!";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person createPerson(@RequestBody @Valid PersonDTO personDTO) {
        return personService.createPerson(personDTO);
    }

}
