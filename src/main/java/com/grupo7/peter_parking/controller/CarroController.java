package com.grupo7.peter_parking.controller;

import com.grupo7.peter_parking.dto.CarroDto;
import com.grupo7.peter_parking.service.CarroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carro")
@Tag(name = "Carro", description = "Endpoints para gerenciar carros")
public class CarroController {

    private final CarroService carroService;

    public CarroController(CarroService carroService) {
        this.carroService = carroService;
    }

    @GetMapping
    @Operation(summary = "Listar todos os carros", description = "Retorna uma lista de todos os carros cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de carros retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CarroDto.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<List<CarroDto>> listarTodos() {
        return ResponseEntity.ok(carroService.listarTodos());
    }

    @GetMapping("/paginado")
    @Operation(summary = "Listar carros paginados", description = "Retorna uma lista paginada de carros cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de carros paginada retornada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisicao invalida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Page<CarroDto>> listarPaginado(
            @Parameter(description = "Informacoes de paginacao") Pageable pageable) {
        return ResponseEntity.ok(carroService.listarPaginado(pageable));
    }

    @GetMapping("/{idCarro}")
    @Operation(summary = "Buscar carro por ID", description = "Busca um carro pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Carro encontrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CarroDto.class))),
            @ApiResponse(responseCode = "404", description = "Carro nao encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<CarroDto> buscarPorId(@PathVariable String idCarro) {
        return ResponseEntity.ok(carroService.buscarPorId(idCarro));
    }

    @PostMapping
    @Operation(summary = "Cadastrar novo carro", description = "Cadastra um novo carro")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Carro cadastrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CarroDto.class))),
            @ApiResponse(responseCode = "400", description = "Requisicao invalida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<CarroDto> salvar(
            @Valid @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do carro a ser cadastrado",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CarroDto.class)))
            CarroDto carroDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carroService.salvar(carroDto));
    }

    @PutMapping("/{idCarro}")
    @Operation(summary = "Atualizar carro", description = "Atualiza os dados de um carro existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Carro atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CarroDto.class))),
            @ApiResponse(responseCode = "404", description = "Carro nao encontrado"),
            @ApiResponse(responseCode = "400", description = "Requisicao invalida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<CarroDto> atualizar(
            @PathVariable String idCarro,
            @Valid @RequestBody CarroDto carroDto) {
        return ResponseEntity.ok(carroService.atualizar(idCarro, carroDto));
    }

    @DeleteMapping("/{idCarro}")
    @Operation(summary = "Deletar carro por ID", description = "Exclui um carro pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Carro deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Carro nao encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> deletarPorId(@PathVariable String idCarro) {
        carroService.deletarPorId(idCarro);
        return ResponseEntity.noContent().build();
    }

}