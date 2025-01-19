package com.grupo7.peter_parking.utils;

import com.grupo7.peter_parking.model.Parquimetro;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class CalculadoraValorTotal {
    public double calcularValorTotal(Parquimetro parquimetro, double valorPorHora) {

        double valorPorHoraAtualizado = this.verificarAdicional(valorPorHora, parquimetro.getEntrada());

        return valorPorHoraAtualizado * parquimetro.getDuracaoEmHoras();
    }

    public double verificarAdicional(double valorPorHora, LocalDateTime entrada){
        if (entrada.getDayOfWeek() == DayOfWeek.SUNDAY || entrada.getDayOfWeek() == DayOfWeek.SATURDAY){
            return valorPorHora * 1.20;
        }
        return valorPorHora;
    }
}
