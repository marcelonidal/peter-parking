package com.grupo7.peter_parking.controller;

import com.grupo7.peter_parking.dto.PessoaDto;
import com.grupo7.peter_parking.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
@Tag(name = "Pessoa", description = "Endpoints para gerenciar pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    @Operation(summary = "Listar todas as pessoas", description = "Retorna uma lista de todas as pessoas cadastradas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de pessoas retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PessoaDto.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<List<PessoaDto>> listarTodos() {
        return ResponseEntity.ok(pessoaService.listarTodos());
    }

    @GetMapping("/paginado")
    @Operation(summary = "Listar pessoas paginadas", description = "Retorna uma lista paginada de pessoas cadastradas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de pessoas paginada retornada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisicao invalida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Page<PessoaDto>> listarPaginado(
            @Parameter(description = "Informacoes de paginacao") Pageable pageable) {
        return ResponseEntity.ok(pessoaService.listarPaginado(pageable));
    }

    @GetMapping("/{idPessoa}")
    @Operation(summary = "Buscar pessoa por ID", description = "Busca uma pessoa pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pessoa encontrada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PessoaDto.class))),
            @ApiResponse(responseCode = "404", description = "Pessoa nao encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<PessoaDto> buscarPorId(@PathVariable String idPessoa) {
        return ResponseEntity.ok(pessoaService.buscarPorId(idPessoa));
    }

    @PostMapping
    @Operation(summary = "Cadastrar nova pessoa", description = "Cadastra uma nova pessoa")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pessoa cadastrada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PessoaDto.class))),
            @ApiResponse(responseCode = "400", description = "Requisicao invalida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<PessoaDto> salvar(
            @Valid @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados da pessoa a ser cadastrada",
                    required = true,
                    content = @Content(schema = @Schema(implementation = PessoaDto.class)))
            PessoaDto pessoaDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.salvar(pessoaDto));
    }

    @PutMapping("/{idPessoa}")
    @Operation(summary = "Atualizar pessoa", description = "Atualiza os dados de uma pessoa existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pessoa atualizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PessoaDto.class))),
            @ApiResponse(responseCode = "404", description = "Pessoa nao encontrada"),
            @ApiResponse(responseCode = "400", description = "Requisicao invalida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<PessoaDto> atualizar(
            @PathVariable String idPessoa,
            @Valid @RequestBody PessoaDto pessoaDto) {
        return ResponseEntity.ok(pessoaService.atualizar(idPessoa, pessoaDto));
    }

    @DeleteMapping("/{idPessoa}")
    @Operation(summary = "Deletar pessoa por ID", description = "Exclui uma pessoa pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Pessoa deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa nao encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> deletarPorId(@PathVariable String idPessoa) {
        pessoaService.deletarPorId(idPessoa);
        return ResponseEntity.noContent().build();
    }

}