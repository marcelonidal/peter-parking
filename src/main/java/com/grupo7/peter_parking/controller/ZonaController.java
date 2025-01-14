package com.grupo7.peter_parking.controller;

import com.grupo7.peter_parking.dto.ZonaDto;
import com.grupo7.peter_parking.service.ZonaService;
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
@RequestMapping("/zona")
@Tag(name= "Zona", description = "Endpoint para gerenciar das zonas dos parquimetros")
public class ZonaController {

    private final ZonaService zonaService;


    public ZonaController(ZonaService zonaService) {
        this.zonaService = zonaService;
    }

    @GetMapping
    @Operation(summary = "Listar todos as zonas", description = "Retorna uma lista de todos as zonas cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de zonas retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ZonaDto.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<List<ZonaDto>> listarTodos(){
        return ResponseEntity.ok(zonaService.listarTodos());
    }

    @GetMapping("/paginado")
    @Operation(summary = "Listar zonas paginadas", description = "Retorna uma lista paginada de zonas cadastradas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de zonas paginada retornada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisicao invalida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Page<ZonaDto>> listarPaginado(
            @Parameter(description = "Informacoes de paginacao") Pageable pageable) {
        return ResponseEntity.ok(zonaService.listarPaginado(pageable));
    }

    @GetMapping("/{idZona}")
    @Operation(summary = "Buscar zona por ID", description = "Busca uma zona pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Zona encontrada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ZonaDto.class))),
            @ApiResponse(responseCode = "404", description = "Zona nao encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<ZonaDto> buscarPorId(@PathVariable String idZona) {
        return ResponseEntity.ok(zonaService.buscarPorId(idZona));
    }

    @PostMapping
    @Operation(summary = "Cadastrar nova zona", description = "Cadastra uma nova zona")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Zona cadastrada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ZonaDto.class))),
            @ApiResponse(responseCode = "400", description = "Requisicao invalida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<ZonaDto> salvar(
            @Valid @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados da zona a ser cadastrado",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ZonaDto.class)))
            ZonaDto zonaDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(zonaService.salvar(zonaDto));
    }

    @PutMapping("/{idZona}")
    @Operation(summary = "Atualizar zona", description = "Atualiza os dados de uma zona existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Zona atualizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ZonaDto.class))),
            @ApiResponse(responseCode = "404", description = "Zona nao encontrada"),
            @ApiResponse(responseCode = "400", description = "Requisicao invalida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<ZonaDto> atualizar(
            @PathVariable String idZona,
            @Valid @RequestBody ZonaDto zonaDto) {
        return ResponseEntity.ok(zonaService.atualizar(idZona, zonaDto));
    }

    @DeleteMapping("/{idZona}")
    @Operation(summary = "Deletar zona por ID", description = "Exclui uma zona pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Zona deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Zona nao encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> deletarPorId(@PathVariable String idZona) {
        zonaService.deletarPorId(idZona);
        return ResponseEntity.noContent().build();
    }
}
