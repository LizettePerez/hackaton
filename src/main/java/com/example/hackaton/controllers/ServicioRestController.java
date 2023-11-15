package com.example.hackaton.controllers;

import com.example.hackaton.models.Servicio;
import com.example.hackaton.repositories.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/servicio")
public class ServicioRestController {

    @Autowired
    ServicioRepository servicioRepository;

    @GetMapping("/lista")
    public List<Servicio> listaServicioPorMonto() {
        List<Servicio> mostrarListaMonto = servicioRepository.findAllByMonto();
        return mostrarListaMonto;
    }


}
