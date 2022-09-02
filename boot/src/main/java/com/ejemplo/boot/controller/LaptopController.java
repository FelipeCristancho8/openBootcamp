package com.ejemplo.boot.controller;

import com.ejemplo.boot.entity.Laptop;
import com.ejemplo.boot.repository.LaptopRepository;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation("Obtiene todas las laptops que hay en el repositorio")
    public List<Laptop> findAll(){
        return this.laptopRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("Obtiene una laptop dado su clave primaria tipo Long, la cual se pasa como PathVariable")
    public ResponseEntity<Laptop> findOneById(@PathVariable Long id){
        Optional<Laptop> laptopOpt = this.laptopRepository.findById(id);
        if(laptopOpt.isPresent()){
            return ResponseEntity.ok(laptopOpt.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ApiOperation("Guarda en base de datos una nueva laptop que se pasa como RequestBody")
    public ResponseEntity<Laptop> createLaptop(@RequestBody Laptop laptop){
        return ResponseEntity.ok(this.laptopRepository.save(laptop));
    }

    @PutMapping
    @ApiOperation("Se actualiza en base de datos una laptop existente que se pasa como RequestBody")
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
    @ApiOperation("Se eliminan todas las filas que pertenecen a la enteidad Laptop")
    public ResponseEntity<Laptop> deleteAll(){
        logger.info("Delete all laptops from repository");
        this.laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Se elimina de base de datos una laptop, dado su clave primaria de tipo Long")
    public ResponseEntity<Laptop> deleteById(@PathVariable Long id){
        if(!this.laptopRepository.existsById(id)){
            logger.warn("Trying to delete a non existent Laptop");
            return ResponseEntity.notFound().build();
        }
        this.laptopRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
