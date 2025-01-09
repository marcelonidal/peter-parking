package com.grupo7.peter_parking.controller;

import com.grupo7.peter_parking.dto.CarroDto;

import com.grupo7.peter_parking.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/carros")
public class CarroController {

    @Autowired
    private CarroService carroService;


    @GetMapping
    public ResponseEntity<List<CarroDto>> getAll() {
        return ResponseEntity.ok(this.carroService.getAll());
    }

    @GetMapping("/placa/{placaDoCarro}")
    public ResponseEntity<CarroDto> getByPlacaDoCarro(@PathVariable String placaDoCarro){
        try {
            CarroDto carroDto = carroService.findByPlacaDoCarro(placaDoCarro);
            return ResponseEntity.ok(carroDto);
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarroDto> getById(@PathVariable String id){
        try {
            CarroDto carroDto = carroService.findById(id);
            return ResponseEntity.ok(carroDto);
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody CarroDto carroDto){
        carroService.save(carroDto);
    }

    @PutMapping("/{placaDoCarro}")
    public ResponseEntity<String> update(@PathVariable String placaDoCarro, @RequestBody CarroDto carroDto){
        try {
            carroService.update(placaDoCarro, carroDto);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{placaDoCarro}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String placaDoCarro){
        carroService.delete(placaDoCarro);
    }
}
