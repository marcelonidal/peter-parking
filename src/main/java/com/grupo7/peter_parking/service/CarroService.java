package com.grupo7.peter_parking.service;

import com.grupo7.peter_parking.dto.CarroDto;
import com.grupo7.peter_parking.model.Carro;
import com.grupo7.peter_parking.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {
    @Autowired
    private CarroRepository repository;

    public List<CarroDto> getAll() {

        return repository.findAll()
                .stream()
                .map(CarroDto::new)
                .toList();
    }

    public CarroDto findByPlacaDoCarro(String placaDoCarro){
        return repository.findCarroByPlacaDoCarro(placaDoCarro)
                .map(CarroDto::new)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado"));
    }

    public CarroDto findById(String id){
        return repository.findById(id)
                .map(CarroDto::new)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado"));
    }

    public void save(CarroDto carroDto){
        repository.save(new Carro(carroDto));
    }

    public void delete(String placaDoCarro){
        Carro carro = repository.findCarroByPlacaDoCarro(placaDoCarro)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado"));
        repository.delete(carro);
    }

    public void update(String placaDoCarro, CarroDto carroDto){
        Carro carroAtualizado = repository.findCarroByPlacaDoCarro(placaDoCarro)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado"));

        carroAtualizado.fromDto(carroDto);
        repository.save(carroAtualizado);
    }
}
