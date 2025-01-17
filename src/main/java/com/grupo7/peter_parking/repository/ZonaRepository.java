package com.grupo7.peter_parking.repository;

import com.grupo7.peter_parking.model.Zona;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ZonaRepository extends MongoRepository<Zona, String> {

    Optional<Zona> findByNome(String nome);
}
