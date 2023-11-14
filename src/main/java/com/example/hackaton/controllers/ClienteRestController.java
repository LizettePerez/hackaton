package com.example.hackaton.controllers;

import com.example.hackaton.models.Cliente;
import com.example.hackaton.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/clientes")
public class ClienteRestController {
    @Autowired
    private ClienteRepository clienteRepository;

    //localhost:8080/clientes/buscarPorRut?rut=9345678-2
    @GetMapping("/buscarPorRut")
    public List<Cliente> buscarPorRut(@RequestParam String rut) {
        return clienteRepository.findByRut(rut);

    }
}