package com.zupinnovation.randomnumberapi.dto.request;

import com.zupinnovation.randomnumberapi.entity.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NumbersDTO {

    private Long id;

    @NotEmpty
    private String number;

    private Person person;
}
