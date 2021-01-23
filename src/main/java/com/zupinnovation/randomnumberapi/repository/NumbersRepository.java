package com.zupinnovation.randomnumberapi.repository;

import com.zupinnovation.randomnumberapi.entity.Numbers;
import com.zupinnovation.randomnumberapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NumbersRepository extends JpaRepository<Numbers, Long> {
    List<Numbers> findByPerson(Person person);
}
