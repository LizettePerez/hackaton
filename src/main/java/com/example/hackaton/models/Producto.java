package com.example.hackaton.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_producto;

    private String nombre;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<Servicio> serviciosProducto;
}
