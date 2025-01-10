package com.grupo7.peter_parking.service;

import com.grupo7.peter_parking.dto.CarroDto;
import java.util.List;

public interface CarroService {    

    List<CarroDto> getAllCarros();

    CarroDto findCarroByPlacaDoCarro(String placaDoCarro);

    CarroDto findCarroById(String id);

    void saveCarro(CarroDto carroDto);

    void deleteCarroByPlacaDoCarro(String placaDoCarro);

    void updateCarroByPlacaDoCarro(String placaDoCarro, CarroDto carroDto);
}
