package com.grupo7.peter_parking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo7.peter_parking.dto.ParquimetroDto;
import com.grupo7.peter_parking.repository.ParquimetroRepository;
import com.grupo7.peter_parking.service.ParquimetroService;

@Service
public class ParquimetroServiceImpl implements ParquimetroService {

    @Autowired
    private ParquimetroRepository repository;


    @Override
    public List<ParquimetroDto> getAllParquimetros() {

        return repository.findAll().stream().map(ParquimetroDto::new).toList();
        
    }    
    
}
