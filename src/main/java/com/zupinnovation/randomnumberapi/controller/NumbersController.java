package com.zupinnovation.randomnumberapi.controller;

import com.zupinnovation.randomnumberapi.dto.request.PersonDTO;
import com.zupinnovation.randomnumberapi.entity.Numbers;
import com.zupinnovation.randomnumberapi.service.NumbersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/number")
public class NumbersController {

    private NumbersService numbersService;

    @Autowired
    public NumbersController(NumbersService numbersService) {
        this.numbersService = numbersService;
    }

    @PostMapping("/drawNumber")
    private Numbers drawNumber(@RequestBody PersonDTO personDTO) {
         return numbersService.drawNumber(personDTO);
    }
}
