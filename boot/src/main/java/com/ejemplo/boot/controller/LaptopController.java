package com.ejemplo.boot.controller;

import com.ejemplo.boot.entity.Laptop;
import com.ejemplo.boot.repository.LaptopRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/laptops")
public class LaptopController {

    private LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @GetMapping
    public List<Laptop> getAll(){
        return this.laptopRepository.findAll();
    }

    @PostMapping
    public void createLaptop(@RequestBody Laptop laptop){
        Objects.nonNull(laptop);
        this.laptopRepository.save(laptop);
    }
}
