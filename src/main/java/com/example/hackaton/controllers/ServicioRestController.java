package com.example.hackaton.controllers;

import com.example.hackaton.models.Servicio;
import com.example.hackaton.repositories.ServicioRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/servicio")

public class ServicioRestController {

    @Autowired
    ServicioRepository servicioRepository;

    @GetMapping("/lista")
    public List<CustomServicioResponse> listaServicioPorMonto() {
        List<Object[]> listaServicios = servicioRepository.findAllByMonto();
        List<CustomServicioResponse> customResponses = new ArrayList<>();

        for (Object[] servicioData : listaServicios) {
            CustomServicioResponse customResponse = new CustomServicioResponse();
            customResponse.setRut((String) servicioData[0]);
            customResponse.setNombreCliente((String) servicioData[1]);
            customResponse.setNombreBanco((String) servicioData[2]);
            customResponse.setIdCuenta((Long) servicioData[3]);
            customResponse.setMonto((Integer) servicioData[4]);
            customResponse.setNombreProducto((String) servicioData[5]);
            customResponse.setIdServicio((Long) servicioData[6]);

            customResponses.add(customResponse);
        }

        return customResponses;
    }
    @Data
    public class CustomServicioResponse {
        private String rut;
        private String nombreCliente;
        private String nombreBanco;
        private Long idCuenta;
        private Integer monto;
        private String nombreProducto;
        private Long idServicio;
    }
}
