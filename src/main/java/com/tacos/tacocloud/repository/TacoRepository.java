package com.tacos.tacocloud.repository;

import com.tacos.tacocloud.entity.Taco;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

public interface TacoRepository extends ReactiveCrudRepository<Taco, UUID> {

}
