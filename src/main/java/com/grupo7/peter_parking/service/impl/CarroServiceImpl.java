package com.grupo7.peter_parking.service.impl;

import com.grupo7.peter_parking.model.Carro;
import com.grupo7.peter_parking.repository.CarroRepository;
import com.grupo7.peter_parking.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroServiceImpl implements CarroService {
    @Autowired
    private CarroRepository carroRepository;

    @Override
    public List<Carro> getAll() {
        return this.carroRepository.findAll();
    }



}
