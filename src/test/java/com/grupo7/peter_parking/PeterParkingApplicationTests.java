package com.grupo7.peter_parking;

import com.grupo7.peter_parking.dto.CarroDto;
import com.grupo7.peter_parking.dto.ParquimetroDto;
import com.grupo7.peter_parking.dto.PessoaDto;
import com.grupo7.peter_parking.dto.ZonaDto;
import com.grupo7.peter_parking.service.CarroService;
import com.grupo7.peter_parking.service.ParquimetroService;
import com.grupo7.peter_parking.service.PessoaService;
import com.grupo7.peter_parking.service.ZonaService;
import com.grupo7.peter_parking.utils.CalculadoraValorTotal;
import com.grupo7.peter_parking.utils.EnvLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PeterParkingApplicationTests {

    @BeforeAll
    static void configurarVariaveisDeAmbiente() {
        EnvLoader.loadEnv("environment/environment-local.env");
    }

    private final CarroService carroService;
    private final PessoaService pessoaService;
    private final ZonaService zonaService;
    private final ParquimetroService parquimetroService;

    @Autowired
    public PeterParkingApplicationTests(CarroService carroService, PessoaService pessoaService, ZonaService zonaService,
                                        ParquimetroService parquimetroService) {
        this.carroService = carroService;
        this.pessoaService = pessoaService;
        this.zonaService = zonaService;
        this.parquimetroService = parquimetroService;
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testarFluxoCompleto() {

        // 1. Criar um Carro
        CarroDto carroDto = new CarroDto(null, "GRP7F14", "Corsa");
        CarroDto carroCriado = carroService.salvar(carroDto);
        assertNotNull(carroCriado);
        assertEquals("GRP7F14", carroCriado.placa());

        // 2. Criar uma Pessoa
        PessoaDto pessoaDto = new PessoaDto(
                null,
                "Joao Silva",
                "92021477010",
                List.of(carroCriado.idCarro()),
                null
        );
        PessoaDto pessoaCriada = pessoaService.salvar(pessoaDto);
        assertNotNull(pessoaCriada);
        assertEquals("Joao Silva", pessoaCriada.nome());

        // 3. Criar uma Zona
        ZonaDto zonaDto = new ZonaDto(null, "Ipiranga", 5.00);
        ZonaDto zonaCriada = zonaService.salvar(zonaDto);
        assertNotNull(zonaCriada);
        assertEquals("Ipiranga", zonaCriada.nome());

        // 4. Criar um Parquimetro - calcularValorTotal
        ParquimetroDto parquimetroDto = new ParquimetroDto(
                null,
                null,
                null,
                2L,
                0.0,
                carroCriado.idCarro(),
                zonaCriada.idZona()
        );
        ParquimetroDto parquimetroCriado = parquimetroService.salvar(parquimetroDto);
        assertNotNull(parquimetroCriado);

        double valorTotal = new CalculadoraValorTotal().verificarAdicional(zonaCriada.valorPorHora(), LocalDateTime.now()) * parquimetroDto.duracaoEmHoras();
        assertEquals(valorTotal, parquimetroCriado.valorTotal());

        // Verificar se o fluxo esta completo e os dados foram persistidos corretamente
        List<CarroDto> carros = carroService.listarTodos();
        assertTrue(carros.stream().anyMatch(carro -> carro.placa().equals("GRP7F14")));

        List<PessoaDto> pessoas = pessoaService.listarTodos();
        assertTrue(pessoas.stream().anyMatch(pessoa -> pessoa.nome().equals("Joao Silva")));

        List<ZonaDto> zonas = zonaService.listarTodos();
        assertTrue(zonas.stream().anyMatch(zona -> zona.nome().equals("Ipiranga")));

        List<ParquimetroDto> parquimetros = parquimetroService.listarTodos();
        assertTrue(parquimetros.stream().anyMatch(parquimetro -> parquimetro.duracaoEmHoras() == 2L));

        //Limpar dados criados no banco
        parquimetroService.deletarPorId(parquimetroCriado.idParquimetro());
        pessoaService.deletarPorId(pessoaCriada.idPessoa());
        carroService.deletarPorId(carroCriado.idCarro());
        zonaService.deletarPorId(zonaCriada.idZona());
    }

}