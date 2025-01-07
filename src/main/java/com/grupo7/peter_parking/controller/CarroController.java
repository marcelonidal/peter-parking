package com.grupo7.peter_parking.controller;

import com.grupo7.peter_parking.model.Carro;

import com.grupo7.peter_parking.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/carros")
public class CarroController {
    @Autowired
    private CarroService carroService;

    @GetMapping
    public List<Carro> getAll() {
        List<Carro> all = this.carroService.getAll();
        return all;
    }
}
