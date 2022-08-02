package com.tacos.tacocloud.controller.rest;

import com.tacos.tacocloud.entity.Taco;
import com.tacos.tacocloud.repository.TacoRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController(value = "RestDesignTacoController")
@RequestMapping(path = "/api/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoController {
    private final TacoRepository tacoRepository;

    @Autowired
    public DesignTacoController(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @GetMapping(path = "/recent")
    @ResponseBody
    public Flux<Taco> recentTacos() {
        return tacoRepository.findAll().take(12);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Taco> postTaco(@RequestBody @Valid Taco taco) {
        return tacoRepository.save(taco);
    }


}
