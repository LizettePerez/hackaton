package com.example.hackaton.controllers;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.hackaton.repositories.ServicioRepository;
import lombok.Data;


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

    //http://localhost:8080/servicio/generarExcel
    @GetMapping("/generarExcel")
    public ResponseEntity<byte[]> generarExcel(@RequestParam(required = false) String rut,
                                               @RequestParam(required = false) List<Integer> dias,
                                               @RequestParam(required = false) String nombreProducto) {

        List<Object[]> listaServicios;

        if (rut != null) {
            listaServicios = servicioRepository.findAllByMontoAndRut(rut);
        } else if (dias != null) {
            listaServicios = servicioRepository.findAllByMontoAndFecha(dias);
        } else if (nombreProducto != null) {
            listaServicios = servicioRepository.findAllByMontoAndNombreProducto(nombreProducto);
        } else {
            listaServicios = servicioRepository.findAllByMonto();
        }

        List<CustomServicioResponse> customResponses = convertirAListaResponse(listaServicios);

        byte[] excelBytes = generarArchivoExcel(customResponses);

        String nombreFiltro = (rut != null) ? rut : (dias != null) ? "fecha_" + dias.get(0) : (nombreProducto != null) ? nombreProducto : "default";

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=resultado_" + nombreFiltro + ".xlsx")
                .body(excelBytes);
    }

    private byte[] generarArchivoExcel(List<CustomServicioResponse> customResponses) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Resultados");

            Row headerRow = sheet.createRow(0);


            String[] columnas = {"Rut", "Nombre Cliente", "Nombre Banco", "ID Cuenta", "Monto", "Nombre Producto", "ID Servicio"};
            for (int i = 0; i < columnas.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columnas[i]);
            }

            int rowNum = 1;
            for (CustomServicioResponse servicio : customResponses) {
                Row dataRow = sheet.createRow(rowNum++);
                dataRow.createCell(0).setCellValue(servicio.getRut());
                dataRow.createCell(1).setCellValue(servicio.getNombreCliente());
                dataRow.createCell(2).setCellValue(servicio.getNombreBanco());
                dataRow.createCell(3).setCellValue(servicio.getIdCuenta());
                dataRow.createCell(4).setCellValue(servicio.getMonto());
                dataRow.createCell(5).setCellValue(servicio.getNombreProducto());
                dataRow.createCell(6).setCellValue(servicio.getIdServicio());
            }


            workbook.write(outputStream);

            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();

        }

        return new byte[0];
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
