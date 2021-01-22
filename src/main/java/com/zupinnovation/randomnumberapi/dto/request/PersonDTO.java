package com.zupinnovation.randomnumberapi.dto.request;

import com.zupinnovation.randomnumberapi.entity.Numbers;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    private Long id;

    @NotEmpty
    @Email
    @Size(min = 5)
    private String email;

    @Valid
    private List<Numbers> numbers;

}