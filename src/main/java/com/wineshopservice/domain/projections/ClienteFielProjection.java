package com.wineshopservice.domain.projections;

public interface ClienteFielProjection {
    Long getId();
    String getNome();
    String getCpf();
    double getValorTotalCompras();
    long getTotalCompras();
}
