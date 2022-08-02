package com.tacos.tacocloud.controller.rest;

import com.tacos.tacocloud.entity.Ingredient;
import com.tacos.tacocloud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

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
    public Flux<Ingredient> getIngredients(){
        return ingredientRepository.findAll();
    }
}
