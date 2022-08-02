package com.tacos.tacocloud.repository;

import com.tacos.tacocloud.entity.TacoUser;
import java.util.UUID;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<TacoUser, UUID> {

    @AllowFiltering
    Mono<TacoUser> findByUsername(String username);

}
