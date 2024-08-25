package com.wineshopservice.service.impl;

import com.wineshopservice.domain.projections.ClienteFielProjection;
import com.wineshopservice.domain.projections.CompraProjection;
import com.wineshopservice.domain.projections.RecomendacaoProjection;
import com.wineshopservice.repository.CompraRepository;
import com.wineshopservice.service.WineshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WineshopServiceImpl implements WineshopService {

    @Autowired
    private CompraRepository compraRepository;

    @Override
    public List<CompraProjection> getComprasOrderPorValorTotal() {
        return compraRepository.findAllComprasOrderByValorTotal();
    }

    @Override
    public CompraProjection getMaiorCompraPorAno(int ano) {
        return compraRepository.findMaiorCompraByAno(ano);
    }

    @Override
    public List<ClienteFielProjection> getTop3ClientesFieis() {
        return compraRepository.findTop3ClientesFieis();
    }

    @Override
    public RecomendacaoProjection getTipoVinhoMaisCompradoPorCliente(String cpf) {
        return compraRepository.findTipoVinhoMaisCompradoPorCliente(cpf);
    }
}
