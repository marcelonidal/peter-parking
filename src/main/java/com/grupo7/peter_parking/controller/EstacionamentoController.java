package com.grupo7.peter_parking.controller;

import com.grupo7.peter_parking.dto.EstacionamentoDto;
import com.grupo7.peter_parking.service.EstacionamentoService;
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
@RequestMapping("/estacionamento")
@Tag(name= "Estacionamento", description = "Endpoint para gerenciar estacionamentos")
public class EstacionamentoController {

    private final EstacionamentoService estacionamentoService;


    public EstacionamentoController(EstacionamentoService estacionamentoService) {
        this.estacionamentoService = estacionamentoService;
    }

    @GetMapping
    @Operation(summary = "Listar todos os estacionamentos", description = "Retorna uma lista de todos os estacionamentos cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de estacionamentos retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EstacionamentoDto.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<List<EstacionamentoDto>> listarTodos(){
        return ResponseEntity.ok(estacionamentoService.listarTodos());
    }

    @GetMapping("/paginado")
    @Operation(summary = "Listar estacionamentos paginados", description = "Retorna uma lista paginada de estacionamentos cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de estacionamentos paginada retornada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisicao invalida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Page<EstacionamentoDto>> listarPaginado(
            @Parameter(description = "Informacoes de paginacao") Pageable pageable) {
        return ResponseEntity.ok(estacionamentoService.listarPaginado(pageable));
    }

    @GetMapping("/{idEstacionamento}")
    @Operation(summary = "Buscar estacionamento por ID", description = "Busca um estacionamento pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estacionamento encontrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EstacionamentoDto.class))),
            @ApiResponse(responseCode = "404", description = "Estacionamento nao encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<EstacionamentoDto> buscarPorId(@PathVariable String idEstacionamento) {
        return ResponseEntity.ok(estacionamentoService.buscarPorId(idEstacionamento));
    }

    @PostMapping
    @Operation(summary = "Cadastrar novo estacionamento", description = "Cadastra um novo estacionamento")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Estacionamento cadastrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EstacionamentoDto.class))),
            @ApiResponse(responseCode = "400", description = "Requisicao invalida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<EstacionamentoDto> salvar(
            @Valid @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do estacionamento a ser cadastrado",
                    required = true,
                    content = @Content(schema = @Schema(implementation = EstacionamentoDto.class)))
            EstacionamentoDto estacionamentoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(estacionamentoService.salvar(estacionamentoDto));
    }

    @PutMapping("/{idEstacionamento}")
    @Operation(summary = "Atualizar estacionamento", description = "Atualiza os dados de um estacionamento existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estacionamento atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EstacionamentoDto.class))),
            @ApiResponse(responseCode = "404", description = "Estacionamento nao encontrado"),
            @ApiResponse(responseCode = "400", description = "Requisicao invalida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<EstacionamentoDto> atualizar(
            @PathVariable String idEstacionamento,
            @Valid @RequestBody EstacionamentoDto estacionamentoDto) {
        return ResponseEntity.ok(estacionamentoService.atualizar(idEstacionamento, estacionamentoDto));
    }

    @DeleteMapping("/{idEstacionamento}")
    @Operation(summary = "Deletar estacionamento por ID", description = "Exclui um estacionamento pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Estacionamento deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Estacionamento nao encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> deletarPorId(@PathVariable String idEstacionamento) {
        estacionamentoService.deletarPorId(idEstacionamento);
        return ResponseEntity.noContent().build();
    }
}
