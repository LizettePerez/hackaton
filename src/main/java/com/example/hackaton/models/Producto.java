package com.example.hackaton.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_producto;

    private String nombre;

  @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_servicio")
    private Servicio servicio;
}
