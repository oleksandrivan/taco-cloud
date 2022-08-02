package com.tacos.tacocloud.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.tacos.tacocloud.entity.Ingredient;
import com.tacos.tacocloud.entity.Order;
import com.tacos.tacocloud.entity.Taco;
import com.tacos.tacocloud.entity.Ingredient.Type;
import com.tacos.tacocloud.repository.IngredientRepository;
import com.tacos.tacocloud.repository.TacoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model) {

        List<Ingredient> ingredients = new ArrayList<>();

        ingredientRepository.findAll().subscribe(ingredients::add);

        Type[] types = Ingredient.Type.values();

        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }

        model.addAttribute("design", taco());
        return "design";
    }

    @PostMapping
    public String processDesign(Model model, @Valid Taco design, Errors errors, @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> log.error("Failed validation with a {}", error.getDefaultMessage()));
            return showDesignForm(model);
        }
        
        log.info("Processing design " + design);
        tacoRepository.save(design).subscribe(order::addDesign);

        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream().filter(ingredient -> ingredient.getType().equals(type))
                .collect(Collectors.toList());
    }
}
