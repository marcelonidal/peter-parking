package com.grupo7.peter_parking.service.impl;

import com.grupo7.peter_parking.dto.PessoaDto;
import com.grupo7.peter_parking.exception.ResourceNotFoundException;
import com.grupo7.peter_parking.mapper.PessoaMapper;
import com.grupo7.peter_parking.model.Pessoa;
import com.grupo7.peter_parking.repository.PessoaRepository;
import com.grupo7.peter_parking.service.CarroService;
import com.grupo7.peter_parking.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;
    private final PessoaMapper pessoaMapper;

    public PessoaServiceImpl(PessoaRepository pessoaRepository, PessoaMapper pessoaMapper) {
        this.pessoaRepository = pessoaRepository;
        this.pessoaMapper = pessoaMapper;
    }

    @Override
    public List<PessoaDto> listarTodos() {
        return pessoaRepository.findAll()
                .stream()
                .map(pessoaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<PessoaDto> listarPaginado(Pageable pageable) {
        return pessoaRepository.findAll(pageable)
                .map(pessoaMapper::toDto);
    }

    @Override
    public PessoaDto buscarPorId(String idPessoa) {
        Pessoa pessoa = pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa nao encontrada com o ID: " + idPessoa));
        return pessoaMapper.toDto(pessoa);
    }

    @Autowired
    private CarroService carroService;
    @Override
    public PessoaDto salvar(PessoaDto pessoaDto) {
        pessoaRepository.findByCpf(pessoaDto.cpf()).ifPresent(existing -> {
            throw new RuntimeException("CPF ja cadastrado: " + pessoaDto.cpf());
        });
        Pessoa pessoa = pessoaMapper.toEntity(pessoaDto, carroService);
        pessoa = pessoaRepository.save(pessoa);
        return pessoaMapper.toDto(pessoa);
    }

    @Override
    public PessoaDto atualizar(String idPessoa, PessoaDto pessoaDto) {
        Pessoa pessoaExistente = pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa nao encontrada com o ID: " + idPessoa));

        pessoaRepository.findByCpf(pessoaDto.cpf()).ifPresent(existing -> {
            if (!existing.getIdPessoa().equals(idPessoa)) {
                throw new RuntimeException("CPF ja cadastrado: " + pessoaDto.cpf());
            }
        });

        pessoaExistente.setNome(pessoaDto.nome());
        pessoaExistente.setCpf(pessoaDto.cpf());

        Pessoa pessoaAtualizada = pessoaRepository.save(pessoaExistente);
        return pessoaMapper.toDto(pessoaAtualizada);
    }

    @Override
    public void deletarPorId(String idPessoa) {
        if (!pessoaRepository.existsById(idPessoa)) {
            throw new ResourceNotFoundException("Pessoa nao encontrada para exclusao com o ID: " + idPessoa);
        }
        pessoaRepository.deleteById(idPessoa);
    }

}