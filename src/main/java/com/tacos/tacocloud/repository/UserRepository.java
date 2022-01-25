package com.tacos.tacocloud.repository;

import com.tacos.tacocloud.entity.TacoUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<TacoUser, Long> {

    TacoUser findByUsername(String username);
}
