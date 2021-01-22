package com.zupinnovation.randomnumberapi.repository;

import com.zupinnovation.randomnumberapi.entity.Numbers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NumbersRepository extends JpaRepository<Numbers, Long> {
}
