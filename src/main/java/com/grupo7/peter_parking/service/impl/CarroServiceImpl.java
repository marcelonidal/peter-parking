package com.grupo7.peter_parking.service.impl;

import com.grupo7.peter_parking.dto.CarroDto;
import com.grupo7.peter_parking.model.Carro;
import com.grupo7.peter_parking.repository.CarroRepository;
import com.grupo7.peter_parking.service.CarroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroServiceImpl implements CarroService {
    @Autowired
    private CarroRepository repository;

    public List<CarroDto> getAllCarros() {

        return repository.findAll()
                .stream()
                .map(CarroDto::new)
                .toList();
    }

    public CarroDto findCarroByPlacaDoCarro(String placaDoCarro){
        return repository.findCarroByPlacaDoCarro(placaDoCarro)
                .map(CarroDto::new)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado"));
    }

    public CarroDto findCarroById(String carroId){
        return repository.findById(carroId)
                .map(CarroDto::new)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado"));
    }

    public void saveCarro(CarroDto carroDto){
        repository.save(new Carro(carroDto));
    }

    public void deleteCarroByPlacaDoCarro(String placaDoCarro){
        Carro carro = repository.findCarroByPlacaDoCarro(placaDoCarro)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado"));
        repository.delete(carro);
    }

    public void updateCarroByPlacaDoCarro(String placaDoCarro, CarroDto carroDto){
        Carro carroAtualizado = repository.findCarroByPlacaDoCarro(placaDoCarro)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado"));

        carroAtualizado.fromDto(carroDto);
        repository.save(carroAtualizado);
    }
}
