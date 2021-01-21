package com.zupinnovation.randomnumberapi.mapper;

import com.zupinnovation.randomnumberapi.dto.request.PersonDTO;
import com.zupinnovation.randomnumberapi.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    Person toModel(PersonDTO personDTO);

    PersonDTO toDTO(Person person);
}
