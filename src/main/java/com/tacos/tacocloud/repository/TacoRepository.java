package com.tacos.tacocloud.repository;

import com.tacos.tacocloud.entity.Taco;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacoRepository extends JpaRepository<Taco, Long> {

}
