package com.tacos.tacocloud.controller.rest;

import com.tacos.tacocloud.entity.Taco;
import com.tacos.tacocloud.repository.TacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

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
    public Collection<Taco> recentTacos() {
        PageRequest pageRequest = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        return tacoRepository.findAll(pageRequest).getContent();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody @Valid Taco taco) {
        return tacoRepository.save(taco);
    }


}
