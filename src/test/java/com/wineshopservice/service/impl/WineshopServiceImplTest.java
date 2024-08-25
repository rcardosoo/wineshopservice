package com.wineshopservice.service.impl;

import com.wineshopservice.domain.projections.ClienteFielProjection;
import com.wineshopservice.domain.projections.CompraProjection;
import com.wineshopservice.domain.projections.RecomendacaoProjection;
import com.wineshopservice.repository.CompraRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WineshopServiceImplTest {

    @Mock
    private CompraRepository compraRepository;

    @InjectMocks
    private WineshopServiceImpl wineshopService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetComprasOrderPorValorTotal() {
        CompraProjection mockCompraProjection = mock(CompraProjection.class);
        List<CompraProjection> expectedCompras = List.of(mockCompraProjection);
        when(compraRepository.findAllComprasOrderByValorTotal()).thenReturn(List.of(mockCompraProjection));

        var result = wineshopService.getComprasOrderPorValorTotal();

        assertEquals(expectedCompras, result);
        verify(compraRepository).findAllComprasOrderByValorTotal();
    }

    @Test
    public void testGetMaiorCompraPorAno() {
        int ano = 2023;
        CompraProjection mockCompraProjection = mock(CompraProjection.class);
        when(compraRepository.findMaiorCompraByAno(ano)).thenReturn(mockCompraProjection);

        var result = wineshopService.getMaiorCompraPorAno(ano);

        assertEquals(mockCompraProjection, result);
        verify(compraRepository).findMaiorCompraByAno(ano);
    }

    @Test
    public void testGetTop3ClientesFieis() {
        ClienteFielProjection mockClienteFielProjection = mock(ClienteFielProjection.class);
        List<ClienteFielProjection> expectedClientes = List.of(mockClienteFielProjection, mockClienteFielProjection, mockClienteFielProjection);
        when(compraRepository.findTop3ClientesFieis()).thenReturn(expectedClientes);

        var result = wineshopService.getTop3ClientesFieis();

        assertEquals(expectedClientes, result);
        verify(compraRepository).findTop3ClientesFieis();
    }

    @Test
    public void testGetTipoVinhoMaisCompradoPorCliente() {
        String cpf = "12345678900";
        RecomendacaoProjection mockRecomendacaoProjection = mock(RecomendacaoProjection.class);
        when(compraRepository.findTipoVinhoMaisCompradoPorCliente(cpf)).thenReturn(mockRecomendacaoProjection);

        var result = wineshopService.getTipoVinhoMaisCompradoPorCliente(cpf);

        assertEquals(mockRecomendacaoProjection, result);
        verify(compraRepository).findTipoVinhoMaisCompradoPorCliente(cpf);
    }
}
