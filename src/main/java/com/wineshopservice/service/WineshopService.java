package com.wineshopservice.service;

import com.wineshopservice.domain.Vinho;
import com.wineshopservice.domain.projections.ClienteFielProjection;
import com.wineshopservice.domain.projections.CompraProjection;
import com.wineshopservice.domain.projections.RecomendacaoProjection;

import java.util.List;

public interface WineshopService {
    List<CompraProjection> getComprasOrderPorValorTotal();

    CompraProjection getMaiorCompraPorAno(int ano);

    List<ClienteFielProjection> getTop3ClientesFieis();

    RecomendacaoProjection getTipoVinhoMaisCompradoPorCliente(String cpf);
}
