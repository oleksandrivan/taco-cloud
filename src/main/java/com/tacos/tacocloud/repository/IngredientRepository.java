package com.tacos.tacocloud.repository;

import com.tacos.tacocloud.dto.Ingredient;

public interface IngredientRepository {

    Iterable<Ingredient> findAll();

    Ingredient findOne(String id);

    Ingredient save(Ingredient ingredient);
}
