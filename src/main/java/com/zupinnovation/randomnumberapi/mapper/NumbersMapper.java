package com.zupinnovation.randomnumberapi.mapper;

import com.zupinnovation.randomnumberapi.dto.request.NumbersDTO;
import com.zupinnovation.randomnumberapi.entity.Numbers;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NumbersMapper {

    NumbersMapper INSTANCE = Mappers.getMapper(NumbersMapper.class);

    Numbers toModel(NumbersDTO numbersDTO);

    NumbersDTO toDTO(Numbers numbers);
}
