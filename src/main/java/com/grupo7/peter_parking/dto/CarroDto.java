package com.grupo7.peter_parking.dto;

import com.grupo7.peter_parking.model.Carro;

public record CarroDto( String placaDoCarro, String modelo) {

    public CarroDto (Carro carro){
        this(carro.getPlacaDoCarro(), carro.getModelo());
    }

}
