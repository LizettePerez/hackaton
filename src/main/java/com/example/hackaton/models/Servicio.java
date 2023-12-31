package com.example.hackaton.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Servicio {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id_servicio;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "id_cliente")
  private Cliente cliente;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "id_producto")
  private Producto producto;


  @JsonFormat(pattern = "dd.MM.yyyy")
  private Date fecha;
  private Integer monto;
}
