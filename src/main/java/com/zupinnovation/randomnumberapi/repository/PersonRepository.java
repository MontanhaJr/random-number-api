package com.zupinnovation.randomnumberapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zupinnovation.randomnumberapi.entity.Person;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByEmail(String email);

}
