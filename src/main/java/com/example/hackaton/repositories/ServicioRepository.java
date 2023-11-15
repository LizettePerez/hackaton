package com.example.hackaton.repositories;

import com.example.hackaton.models.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {

    @Query(value = "SELECT c.rut, c.nombre, cu.nombre_banco, cu.id_cuenta, s.monto, p.nombre AS nombre_producto, s.id_servicio " +
            "FROM servicio s " +
            "JOIN cliente c ON s.id_cliente = c.id_cliente " +
            "JOIN producto p ON s.id_producto = p.id_producto " +
            "JOIN cuenta cu ON cu.id_cliente = c.id_cliente " +
            "ORDER BY s.monto", nativeQuery = true)
    List<Object[]> findAllByMonto();

    @Query(value = "SELECT c.rut, c.nombre, cu.nombre_banco, cu.id_cuenta, s.monto, p.nombre AS nombre_producto, s.id_servicio " +
            "FROM servicio s " +
            "JOIN cliente c ON s.id_cliente = c.id_cliente " +
            "JOIN producto p ON s.id_producto = p.id_producto " +
            "JOIN cuenta cu ON cu.id_cliente = c.id_cliente " +
            "WHERE c.rut = :rut " +  // Agregamos la cláusula WHERE para filtrar por el rut
            "ORDER BY s.monto", nativeQuery = true)
    List<Object[]> findAllByMontoAndRut(@Param("rut") String rut);

    @Query(value = "SELECT c.rut, c.nombre, cu.nombre_banco, cu.id_cuenta, s.monto, p.nombre AS nombre_producto, s.id_servicio " +
            "FROM servicio s " +
            "JOIN cliente c ON s.id_cliente = c.id_cliente " +
            "JOIN producto p ON s.id_producto = p.id_producto " +
            "JOIN cuenta cu ON cu.id_cliente = c.id_cliente " +
            "WHERE DATE_FORMAT(s.fecha, '%d') IN ('05', '10', '15', '20') " +
            "ORDER BY s.monto", nativeQuery = true)
    List<Object[]> findAllByMontoAndFecha();

    @Query(value = "SELECT c.rut, c.nombre, cu.nombre_banco, cu.id_cuenta, s.monto, p.nombre AS nombre_producto, s.id_servicio " +
            "FROM servicio s " +
            "JOIN cliente c ON s.id_cliente = c.id_cliente " +
            "JOIN producto p ON s.id_producto = p.id_producto " +
            "JOIN cuenta cu ON cu.id_cliente = c.id_cliente " +
            "WHERE p.nombre = :nombreProducto " +
            "ORDER BY s.monto", nativeQuery = true)
    List<Object[]> findAllByMontoAndNombreProducto(@Param("nombreProducto") String nombreProducto);
}
