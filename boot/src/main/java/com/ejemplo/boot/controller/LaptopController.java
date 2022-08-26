package com.ejemplo.boot.controller;

import com.ejemplo.boot.entity.Laptop;
import com.ejemplo.boot.repository.LaptopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/laptops")
public class LaptopController {

    private final Logger logger = LoggerFactory.getLogger(LaptopController.class);

    private LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @GetMapping
    public List<Laptop> findAll(){
        return this.laptopRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Laptop> findOneById(@PathVariable Long id){
        Optional<Laptop> laptopOpt = this.laptopRepository.findById(id);
        if(laptopOpt.isPresent()){
            return ResponseEntity.ok(laptopOpt.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void createLaptop(@RequestBody Laptop laptop){
        this.laptopRepository.save(laptop);
    }

    @PutMapping
    public ResponseEntity<Laptop> updateLaptop(@RequestBody Laptop laptop){
        if(laptop.getId() == null){
            return ResponseEntity.badRequest().build();
        }
        if(!this.laptopRepository.existsById(laptop.getId())){
            return ResponseEntity.notFound().build();
        }
        Laptop result = this.laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping
    public ResponseEntity<Laptop> deleteAll(){
        logger.info("Delete all laptops from repository");
        this.laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Laptop> deleteById(@PathVariable Long id){
        if(!this.laptopRepository.existsById(id)){
            logger.warn("Trying to delete a non existent Laptop");
            return ResponseEntity.notFound().build();
        }
        this.laptopRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
