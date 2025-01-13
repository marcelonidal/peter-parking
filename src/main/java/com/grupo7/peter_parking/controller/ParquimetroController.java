package com.grupo7.peter_parking.controller;

import com.grupo7.peter_parking.dto.ParquimetroDto;
import com.grupo7.peter_parking.service.ParquimetroService;
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
@RequestMapping("/parquimetro")
@Tag(name = "Parquimetro", description = "Endpoints para gerenciar parquimetros")
public class ParquimetroController {

    private final ParquimetroService parquimetroService;

    public ParquimetroController(ParquimetroService parquimetroService) {
        this.parquimetroService = parquimetroService;
    }

    @GetMapping
    @Operation(summary = "Listar todos os parquimetros", description = "Retorna uma lista de todos os parquimetros cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de parquimetros retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ParquimetroDto.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<List<ParquimetroDto>> listarTodos() {
        return ResponseEntity.ok(parquimetroService.listarTodos());
    }

    @GetMapping("/paginado")
    @Operation(summary = "Listar parquimetros paginados", description = "Retorna uma lista paginada de parquimetros cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de parquimetros paginada retornada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisicao invalida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Page<ParquimetroDto>> listarPaginado(
            @Parameter(description = "Informacoes de paginacao") Pageable pageable) {
        return ResponseEntity.ok(parquimetroService.listarPaginado(pageable));
    }

    @GetMapping("/{idParquimetro}")
    @Operation(summary = "Buscar parquimetro por ID", description = "Busca um parquimetro pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Parquimetro encontrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ParquimetroDto.class))),
            @ApiResponse(responseCode = "404", description = "Parquimetro nao encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<ParquimetroDto> buscarPorId(@PathVariable String idParquimetro) {
        return ResponseEntity.ok(parquimetroService.buscarPorId(idParquimetro));
    }

    @PostMapping
    @Operation(summary = "Cadastrar novo parquimetro", description = "Cadastra um novo parquimetro")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Parquimetro cadastrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ParquimetroDto.class))),
            @ApiResponse(responseCode = "400", description = "Requisicao invalida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<ParquimetroDto> salvar(
            @Valid @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do parquimetro a ser cadastrado",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ParquimetroDto.class)))
            ParquimetroDto parquimetroDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(parquimetroService.salvar(parquimetroDto));
    }

    @PutMapping("/{idParquimetro}")
    @Operation(summary = "Atualizar parquimetro", description = "Atualiza os dados de um parquimetro existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Parquimetro atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ParquimetroDto.class))),
            @ApiResponse(responseCode = "404", description = "Parquimetro nao encontrado"),
            @ApiResponse(responseCode = "400", description = "Requisicao invalida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<ParquimetroDto> atualizar(
            @PathVariable String idParquimetro,
            @Valid @RequestBody ParquimetroDto parquimetroDto) {
        return ResponseEntity.ok(parquimetroService.atualizar(idParquimetro, parquimetroDto));
    }

    @DeleteMapping("/{idParquimetro}")
    @Operation(summary = "Deletar parquimetro por ID", description = "Exclui um parquimetro pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Parquimetro deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Parquimetro nao encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> deletarPorId(@PathVariable String idParquimetro) {
        parquimetroService.deletarPorId(idParquimetro);
        return ResponseEntity.noContent().build();
    }

}