package com.grupo7.peter_parking.repository;

import com.grupo7.peter_parking.model.Carro;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CarroRepository extends MongoRepository<Carro, String> {

    Optional<Carro> findCarroByPlacaDoCarro(String placaDoCarro);
}
