package com.tacos.tacocloud.controller.rest;

import com.tacos.tacocloud.entity.Ingredient;
import com.tacos.tacocloud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "api/ingredients", produces = "application/json")
@CrossOrigin(origins = "*")
public class IngredientsController {
    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientsController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping
    @ResponseBody
    public Iterable<Ingredient> getIngredients(){
        return ingredientRepository.findAll();
    }
}
