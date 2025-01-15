package com.grupo7.peter_parking.service.impl;

import com.grupo7.peter_parking.dto.CarroDto;
import com.grupo7.peter_parking.exception.ResourceNotFoundException;
import com.grupo7.peter_parking.mapper.CarroMapper;
import com.grupo7.peter_parking.model.Carro;
import com.grupo7.peter_parking.repository.CarroRepository;
import com.grupo7.peter_parking.service.CarroService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarroServiceImpl implements CarroService {

    private final CarroRepository carroRepository;
    private final CarroMapper carroMapper;

    public CarroServiceImpl(CarroRepository carroRepository, CarroMapper carroMapper) {
        this.carroRepository = carroRepository;
        this.carroMapper = carroMapper;
    }

    @Override
    public List<CarroDto> listarTodos() {
        return carroRepository.findAll()
                .stream()
                .map(carroMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<CarroDto> listarPaginado(Pageable pageable) {
        return carroRepository.findAll(pageable)
                .map(carroMapper::toDto);
    }

    @Override
    public CarroDto buscarPorId(String idCarro) {
        Carro carro = carroRepository.findById(idCarro)
                .orElseThrow(() -> new ResourceNotFoundException("Carro nao encontrado com o ID: " + idCarro));
        return carroMapper.toDto(carro);
    }

    @Override
    public CarroDto buscarPorPlaca(String placa) {
        Carro carro = carroRepository.findByPlaca(placa)
                .orElseThrow(() -> new ResourceNotFoundException("Carro nao encontrado com a placa: " + placa));
        return carroMapper.toDto(carro);
    }

    @Override
    public CarroDto salvar(CarroDto carroDto) {
        Carro carro = carroMapper.toEntity(carroDto);
        carro = carroRepository.save(carro);
        return carroMapper.toDto(carro);
    }

    @Override
    public CarroDto atualizar(String idCarro, CarroDto carroDto) {
        Carro carroExistente = carroRepository.findById(idCarro)
                .orElseThrow(() -> new ResourceNotFoundException("Carro nao encontrado com o ID: " + idCarro));

        // Atualiza os campos do carro
        carroExistente.setPlaca(carroDto.placa());
        carroExistente.setModelo(carroDto.modelo());

        // Salva no banco e retorna o DTO atualizado
        Carro carroAtualizado = carroRepository.save(carroExistente);
        return carroMapper.toDto(carroAtualizado);
    }

    @Override
    public void deletarPorId(String idCarro) {
        if (!carroRepository.existsById(idCarro)) {
            throw new ResourceNotFoundException("Carro nao encontrado para exclusao com o ID: " + idCarro);
        }
        carroRepository.deleteById(idCarro);
    }

    @Override
    public List<Carro> findAllByIds(List<String> ids) {
        return carroRepository.findAllById(ids);
    }

}