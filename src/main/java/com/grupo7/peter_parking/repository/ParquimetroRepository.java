package com.grupo7.peter_parking.repository;

import com.grupo7.peter_parking.model.Parquimetro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParquimetroRepository extends MongoRepository<Parquimetro, String> {

    List<Parquimetro> findByCarro_IdCarro(String idCarro);

    List<Parquimetro> findByZona(String idZona);

}
