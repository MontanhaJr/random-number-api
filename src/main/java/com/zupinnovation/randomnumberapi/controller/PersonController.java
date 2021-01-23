package com.zupinnovation.randomnumberapi.controller;

import com.zupinnovation.randomnumberapi.dto.request.PersonDTO;
import com.zupinnovation.randomnumberapi.dto.response.MessageResponseDTO;
import com.zupinnovation.randomnumberapi.entity.Person;
import com.zupinnovation.randomnumberapi.exception.EmailNotFoundException;
import com.zupinnovation.randomnumberapi.exception.PersonNotFoundException;
import com.zupinnovation.randomnumberapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/listAll")
    public List<Person> listAll() {
        return personService.listAll();
    }

    @GetMapping("/listByEmail")
    public Person findByEmail(@RequestBody PersonDTO personDTO) throws EmailNotFoundException {
        return personService.findByEmail(personDTO.getEmail());
    }

//    @GetMapping("/{email}")
//    public Person findByEmailURL(@PathVariable String email) throws EmailNotFoundException {
//        return personService.findByEmail(email);
//    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Person createPerson(@RequestBody @Valid PersonDTO personDTO) {
//        return personService.createPerson(personDTO);
//    }

}
