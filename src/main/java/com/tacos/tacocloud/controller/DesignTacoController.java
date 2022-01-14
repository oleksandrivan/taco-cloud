package com.tacos.tacocloud.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.tacos.tacocloud.dto.Ingredient;
import com.tacos.tacocloud.dto.Ingredient.Type;
import com.tacos.tacocloud.dto.Order;
import com.tacos.tacocloud.dto.Taco;
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

        // List<Ingredient> ingredients = Arrays.asList(
        // new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
        // new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
        // new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
        // new Ingredient("CARN", "Carnitas", Type.PROTEIN),
        // new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
        // new Ingredient("LETC", "Lettuce", Type.VEGGIES),
        // new Ingredient("CHED", "Cheddar", Type.CHEESE),
        // new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
        // new Ingredient("SLSA", "Salsa", Type.SAUCE),
        // new Ingredient("SRCR", "Sour Cream", Type.SAUCE));

        List<Ingredient> ingredients = new ArrayList<>();

        ingredientRepository.findAll().forEach(ingredient -> ingredients.add(ingredient));

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
        Taco saved = tacoRepository.save(design);
        order.addDesign(saved);

        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream().filter(ingredient -> ingredient.getType().equals(type))
                .collect(Collectors.toList());
    }
}
