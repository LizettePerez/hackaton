package com.example.hackaton.repositories;

import com.example.hackaton.models.Cliente;
import com.example.hackaton.models.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {


}
