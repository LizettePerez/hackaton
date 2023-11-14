package com.example.hackaton.repositories;

import com.example.hackaton.models.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {

    @Query(value = "SELECT * FROM servicio ORDER BY monto", nativeQuery = true)
    List<Servicio> findAllByMonto();

    @Query(value = "SELECT * FROM servicio ORDER BY fecha", nativeQuery = true)
    List<Servicio> findAllByFecha();
}