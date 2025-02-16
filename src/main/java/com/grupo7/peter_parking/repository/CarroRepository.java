package com.grupo7.peter_parking.repository;

import com.grupo7.peter_parking.model.Carro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarroRepository extends MongoRepository<Carro, String> {

    Optional<Carro> findByPlaca(String placa);
}
