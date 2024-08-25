package com.wineshopservice.service.impl;

import com.wineshopservice.client.DadosApiClient;
import com.wineshopservice.client.dto.CompraDto;
import com.wineshopservice.domain.Cliente;
import com.wineshopservice.domain.Compra;
import com.wineshopservice.repository.ClienteRepository;
import com.wineshopservice.repository.CompraRepository;
import com.wineshopservice.repository.VinhoRepository;
import com.wineshopservice.service.ImportaDadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImportaDadosServiceImpl implements ImportaDadosService {

    @Autowired
    private DadosApiClient client;

    @Autowired
    private VinhoRepository vinhoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CompraRepository compraRepository;

    @Override
    public void sincronizar() {
        limparDados();
        gravarVinhos();
        gravarClientes();
    }

    private void limparDados() {
        compraRepository.deleteAllInBatch();
        clienteRepository.deleteAllInBatch();
        vinhoRepository.deleteAllInBatch();
    }

    private void gravarVinhos() {
        var vinhosResponse = client.getVinhos();
        var vinhos = vinhosResponse.getBody();
        if (vinhos != null && !vinhos.isEmpty()) {
            vinhos.forEach(vinhoDto -> {
                vinhoRepository.save(vinhoDto.toVinho());
            });
        }
    }

    private void gravarClientes() {
        var clientesResponse = client.getClientes();
        var clientes = clientesResponse.getBody();
        if (clientes != null && !clientes.isEmpty()) {
            clientes.forEach(clienteDto -> {
                var cliente = clienteRepository.save(clienteDto.toCliente());
                gravarComprasPorCliente(cliente, clienteDto.getCompras());
            });
        }
    }

    private void gravarComprasPorCliente(Cliente cliente, List<CompraDto> compras) {
        if (compras != null && !compras.isEmpty()) {
            compras.forEach(compraDto -> {
                compraRepository.save(Compra.builder()
                    .vinho(vinhoRepository
                        .findById(Long.valueOf(compraDto.getCodigo()))
                        .orElseThrow(() -> new RuntimeException("Vinho não encontrado")))
                    .cliente(cliente)
                    .quantidade(compraDto.getQuantidade())
                    .build());
            });
        }
    }

}
