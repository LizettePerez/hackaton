package com.example.hackaton.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

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



  private Date fecha;
  private Integer monto;
}
