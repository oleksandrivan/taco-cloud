package com.tacos.tacocloud.flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

public class FluxTest {

  @Test
  void createAFluxFromArray() {
    String[] fruitArray = new String[]{ "Apple", "Orange", "Grape", "Banana", "Strawberry"};
      Flux<String> fruitFlux = Flux.fromArray(fruitArray);
      StepVerifier.create(fruitFlux).expectNext("Apple").expectNext("Orange").expectNext("Grape")
          .expectNext("Banana").expectNext("Strawberry").verifyComplete();
  }

  @Test
  void createAFluxFromStream() {
    Stream<String> fruitStream = Stream.of("Apple", "Orange", "Grape", "Banana", "Strawberry");
    Flux<String> fruitFlux = Flux.fromStream(fruitStream);
    StepVerifier.create(fruitFlux).expectNext("Apple").expectNext("Orange").expectNext("Grape")
        .expectNext("Banana").expectNext("Strawberry").verifyComplete();
  }

  @Test
  void createAFluxFromRange() {
    Flux<Integer> integerFlux = Flux.range(1,5);
    StepVerifier.create(integerFlux)
        .expectNext(1)
        .expectNext(2)
        .expectNext(3)
        .expectNext(4)
        .expectNext(5)
        .verifyComplete();
  }

  @Test
  void mergeFlux() {
    Flux<String> characters = Flux.just("Garfield", "Kojak", "Barbossa")
        .delayElements(Duration.ofMillis(500));
    Flux<String> food = Flux.just("Lasagna", "Lollipops", "Apples")
        .delaySubscription(Duration.ofMillis(250)).delayElements(Duration.ofMillis(500));

    StepVerifier.create(characters.mergeWith(food))
        .expectNext("Garfield")
        .expectNext("Lasagna")
        .expectNext("Kojak")
        .expectNext("Lollipops")
        .expectNext("Barbossa")
        .expectNext("Apples")
        .verifyComplete();
  }

  @Test
  void zipFlux() {
    Flux<String> characters = Flux.just("Garfield", "Kojak", "Barbossa");
    Flux<String> food = Flux.just("Lasagna", "Lollipops", "Apples");

    StepVerifier.create(characters.zipWith(food))
        .expectNextMatches(objects -> objects.getT1().equals("Garfield") && objects.getT2().equals("Lasagna"))
        .expectNextMatches(objects -> objects.getT1().equals("Kojak") && objects.getT2().equals("Lollipops"))
        .expectNextMatches(objects -> objects.getT1().equals("Barbossa") && objects.getT2().equals("Apples"))
        .verifyComplete();
  }

  @Test
  void zipFluxWithFunction(){
    Flux<String> characters = Flux.just("Garfield", "Kojak", "Barbossa");
    Flux<String> food = Flux.just("Lasagna", "Lollipops", "Apples");

    Flux<String> zipped = characters.zipWith(food, (s, s2) -> String.format("%s eats %s", s, s2));
    StepVerifier.create(zipped)
        .expectNext("Garfield eats Lasagna")
        .expectNext("Kojak eats Lollipops")
        .expectNext("Barbossa eats Apples")
        .verifyComplete();
  }

  @Test
  void firstFlux(){
    Flux<String> slowFlux = Flux.just("tortoise", "snail", "sloth").delaySubscription(Duration.ofMillis(200));
    Flux<String> fastFlux = Flux.just("hare", "cheetah", "squirrel");

    StepVerifier.create(Flux.firstWithSignal(fastFlux, slowFlux))
        .expectNext("hare")
        .expectNext("cheetah")
        .expectNext("squirrel")
        .verifyComplete();
  }

  @Test
  void bufferFlux(){
    Flux<String> fruitFlux = Flux.just("Apple", "Orange", "Grape", "Banana", "Strawberry");

    Flux<List<String>> bufferFlux = fruitFlux.buffer(3);

    StepVerifier.create(bufferFlux)
        .expectNext(Arrays.asList("Apple", "Orange", "Grape"))
        .expectNext(Arrays.asList("Banana", "Strawberry"))
        .verifyComplete();
  }

  @Test
  void parallelFlux(){
    Flux.just(
            "apple", "orange", "banana", "kiwi", "strawberry")
        .buffer(3)
        .flatMap(x ->
            Flux.fromIterable(x)
                .map(y -> y.toUpperCase())
                .subscribeOn(Schedulers.parallel())
                .log()
        ).subscribe();
  }

  @Test
  void collectMap() {
    Flux<String> animalFlux = Flux.just("aardvark", "elephant", "koala", "eagle", "kangaroo");
    Mono<Map<Object, String>> animalMap = animalFlux.collectMap(s -> s.charAt(0));

    StepVerifier.create(animalMap)
        .expectNextMatches(objectStringMap ->
            objectStringMap.size() == 3 &&
                objectStringMap.get('e').equals("eagle") &&
                objectStringMap.get('k').equals("kangaroo")
        ).verifyComplete();

  }
}
