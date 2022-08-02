package com.tacos.tacocloud.config;

import com.tacos.tacocloud.entity.Ingredient;
import com.tacos.tacocloud.entity.Ingredient.Type;
import com.tacos.tacocloud.repository.IngredientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@AllArgsConstructor
@Slf4j
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

  private final IngredientRepository ingredientRepository;
  /**
   * This event is executed as late as conceivably possible to indicate that
   * the application is ready to service requests.
   */
  @Override
  public void onApplicationEvent(final ApplicationReadyEvent event) {
    ingredientRepository.deleteAll();

    ingredientRepository.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP)).block();
    ingredientRepository.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP)).block();
    ingredientRepository.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN)).block();
    ingredientRepository.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN)).block();
    ingredientRepository.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES)).block();
    ingredientRepository.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES)).block();
    ingredientRepository.save(new Ingredient("CHED", "Cheddar", Type.CHEESE)).block();
    ingredientRepository.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE)).block();
    ingredientRepository.save(new Ingredient("SLSA", "Salsa", Type.SAUCE)).block();
    ingredientRepository.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE)).block();
    log.info("Application ready :)");
  }
}
