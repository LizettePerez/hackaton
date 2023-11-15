package com.example.hackaton.repositories;

import com.example.hackaton.models.Cliente;
import com.example.hackaton.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query("SELECT p, s, c FROM Producto p " +
            "JOIN FETCH p.servicio s " +
            "JOIN FETCH s.cliente c " +
            "WHERE p.nombre = :nombreProducto")
    List<Object[]> findByNombreProducto(@Param("nombreProducto") String nombreProducto);

}
