package com.ejemplo.boot.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "laptops")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Entidad laptop para representar un computador")
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("Clave ficticia autoincremental tipo long")
    private Long id;
    @ApiModelProperty("Nombre del modelo del computador, tipo String")
    private String name;
    @ApiModelProperty("Marca del computador, tipo String")
    private String brand;
    @Column(name = "manufacturing_date")
    @ApiModelProperty("Fecha de lanzamiento del computador, tipo LocalDate")
    private LocalDate manufacturingDate;

}
