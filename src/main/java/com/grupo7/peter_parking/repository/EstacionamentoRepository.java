package com.grupo7.peter_parking.repository;

import com.grupo7.peter_parking.model.Estacionamento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstacionamentoRepository extends MongoRepository<Estacionamento, String> {
}
