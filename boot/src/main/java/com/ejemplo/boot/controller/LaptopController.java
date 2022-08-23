package com.ejemplo.boot.controller;

import com.ejemplo.boot.entity.Laptop;
import com.ejemplo.boot.repository.LaptopRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
