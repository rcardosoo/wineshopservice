package com.wineshopservice.domain.projections;

public interface CompraProjection {
    String getNomeCliente();
    String getCpfCliente();
    String getTipoVinho();
    double getPreco();
    String getSafra();
    int getAnoCompra();
    int getQuantidade();
    double getValorTotal();
}
