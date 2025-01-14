package com.grupo7.peter_parking.utils;

import com.grupo7.peter_parking.model.Parquimetro;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class CalculadoraValorTotal {
    public BigDecimal calcularValorTotal(Parquimetro parquimetro, BigDecimal valorPorHora) {

        BigDecimal valorPorHoraAtualizado = this.verificarAdicional(valorPorHora,
                parquimetro.getEntrada());

        return valorPorHoraAtualizado.multiply(BigDecimal.valueOf(parquimetro.getDuracaoEmHoras()));
    }

    public BigDecimal verificarAdicional(BigDecimal valorPorHora, LocalDateTime entrada){
        if (entrada.getDayOfWeek() == DayOfWeek.SUNDAY || entrada.getDayOfWeek() == DayOfWeek.SATURDAY){
            return valorPorHora.multiply(BigDecimal.valueOf(1.20));
        }
        return valorPorHora;
    }
}
