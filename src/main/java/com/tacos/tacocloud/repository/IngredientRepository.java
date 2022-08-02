package com.tacos.tacocloud.repository;

import com.tacos.tacocloud.entity.Ingredient;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IngredientRepository extends ReactiveCrudRepository<Ingredient, String> {

}
