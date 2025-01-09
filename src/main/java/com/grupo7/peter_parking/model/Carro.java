package com.grupo7.peter_parking.model;

import com.grupo7.peter_parking.dto.CarroDto;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Carro {

    @Id
    private String codigo;

    private String placaDoCarro;

    private String modelo;

    public Carro(){}

    public Carro (CarroDto dto){
       this.placaDoCarro = dto.placaDoCarro();
       this.modelo = dto.modelo();
    }

    public void fromDto(CarroDto dto){
        this.placaDoCarro = dto.placaDoCarro();
        this.modelo = dto.modelo();
    }
}
