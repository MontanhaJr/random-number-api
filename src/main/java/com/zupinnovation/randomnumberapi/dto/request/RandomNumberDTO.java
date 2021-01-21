package com.zupinnovation.randomnumberapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RandomNumberDTO {

    private Long id;

    @NotEmpty
    private Integer number;
}
