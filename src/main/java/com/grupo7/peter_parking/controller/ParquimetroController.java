package com.grupo7.peter_parking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo7.peter_parking.dto.ParquimetroDto;
import com.grupo7.peter_parking.service.ParquimetroService;

@RestController
@RequestMapping(value = "/vagas")
public class ParquimetroController {    

    @Autowired
    private ParquimetroService parquimetroService;

    @GetMapping
    public ResponseEntity<List<ParquimetroDto>> getAll() {
        return ResponseEntity.ok(this.parquimetroService.getAllParquimetros());
    }

}
