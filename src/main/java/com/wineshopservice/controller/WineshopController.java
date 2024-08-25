package com.wineshopservice.controller;

import com.wineshopservice.domain.projections.ClienteFielProjection;
import com.wineshopservice.domain.projections.CompraProjection;
import com.wineshopservice.domain.projections.RecomendacaoProjection;
import com.wineshopservice.service.WineshopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/wineshop")
public class WineshopController {

    @Autowired
    private WineshopService wineshopService;

    @Operation(summary = "Obter todas as compras ordenadas por valor total")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de compras ordenadas por valor total")
    })
    @GetMapping("/compras")
    public ResponseEntity<List<CompraProjection>> getComprasOrderPorValorTotal() {
        return ResponseEntity.ok(wineshopService.getComprasOrderPorValorTotal());
    }

    @Operation(summary = "Obter a maior compra de um ano específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Maior compra do ano informado"),
        @ApiResponse(responseCode = "400", description = "Ano inválido")
    })
    @GetMapping("/maior-compra/ano")
    public ResponseEntity<CompraProjection> getMaiorCompraPorAno(
        @Parameter(description = "Ano para buscar a maior compra", required = true) @RequestParam int ano) {
        return ResponseEntity.ok(wineshopService.getMaiorCompraPorAno(ano));
    }

    @Operation(summary = "Obter os 3 principais clientes mais fiéis")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Top 3 clientes mais fiéis")
    })
    @GetMapping("/clientes-fieis")
    public ResponseEntity<List<ClienteFielProjection>> getTop3ClientesFieis() {
        return ResponseEntity.ok(wineshopService.getTop3ClientesFieis());
    }

    @Operation(summary = "Obter recomendação de vinho para um cliente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recomendação de vinho baseada no histórico de compras do cliente"),
        @ApiResponse(responseCode = "400", description = "CPF do cliente inválido")
    })
    @GetMapping("/recomendacao/cliente/tipo")
    public ResponseEntity<RecomendacaoProjection> getTipoVinhoMaisCompradoPorCliente(
        @Parameter(description = "CPF do cliente para buscar a recomendação de vinho", required = true) @RequestParam String cpf) {
        return ResponseEntity.ok(wineshopService.getTipoVinhoMaisCompradoPorCliente(cpf));
    }
}
