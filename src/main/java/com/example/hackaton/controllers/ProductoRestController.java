package com.example.hackaton.controllers;

import com.example.hackaton.models.Producto;
import com.example.hackaton.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/productos")
public class ProductoRestController {

    }
