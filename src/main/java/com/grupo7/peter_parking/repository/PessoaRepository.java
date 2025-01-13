package com.grupo7.peter_parking.repository;

import com.grupo7.peter_parking.model.Pessoa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends MongoRepository<Pessoa, String> {

    Optional<Pessoa> findByCpf(String cpf);
}