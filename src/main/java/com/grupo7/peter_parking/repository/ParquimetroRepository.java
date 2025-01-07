package com.grupo7.peter_parking.repository;

import com.grupo7.peter_parking.model.Parquimetro;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParquimetroRepository extends MongoRepository<Parquimetro, String> {


}
