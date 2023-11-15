package com.example.hackaton.controllers;

import com.example.hackaton.models.Servicio;
import com.example.hackaton.repositories.ServicioRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/servicio")

public class ServicioRestController {

    @Autowired
    ServicioRepository servicioRepository;


    //http://localhost:8080/servicio/lista
    @GetMapping("/lista")
    public List<CustomServicioResponse> listaServicioPorMonto() {
        List<Object[]> listaServicios = servicioRepository.findAllByMonto();
        return convertirAListaResponse(listaServicios);
    }

    //http://localhost:8080/servicio/listaPorRut?rut=12
    @GetMapping("/listaPorRut")
    public List<CustomServicioResponse> listaServicioPorRutYMonto(@RequestParam String rut) {
        List<Object[]> listaServicios = servicioRepository.findAllByMontoAndRut(rut);
        return convertirAListaResponse(listaServicios);
    }

    //http://localhost:8080/servicio/listaPorFecha?dias=05
    @GetMapping("/listaPorFecha")
    public List<CustomServicioResponse> listaServicioPorFechaYMonto(@RequestParam List<Integer> dias) {
        List<Object[]> listaServicios = servicioRepository.findAllByMontoAndFecha(dias);
        return convertirAListaResponse(listaServicios);
    }


    //http://localhost:8080/servicio/listaPorProducto?nombreProducto=Mis%20Metas
    @GetMapping("/listaPorProducto")
    public List<CustomServicioResponse> listaServicioPorNombreProductoYMonto(@RequestParam String nombreProducto) {
        List<Object[]> listaServicios = servicioRepository.findAllByMontoAndNombreProducto(nombreProducto);
        return convertirAListaResponse(listaServicios);
    }

    private List<CustomServicioResponse> convertirAListaResponse(List<Object[]> listaServicios) {
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
