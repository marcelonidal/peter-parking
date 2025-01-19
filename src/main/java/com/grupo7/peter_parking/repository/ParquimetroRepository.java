package com.grupo7.peter_parking.repository;

import com.grupo7.peter_parking.model.Parquimetro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParquimetroRepository extends MongoRepository<Parquimetro, String> {

    Optional<Parquimetro> findByCarro_IdCarroAndZona_IdZona(String idCarro, String idZona);
}
