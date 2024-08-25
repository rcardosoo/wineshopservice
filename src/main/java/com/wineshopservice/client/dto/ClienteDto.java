package com.wineshopservice.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wineshopservice.domain.Cliente;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ClienteDto {
    @JsonProperty(value = "nome")
    private String nome;
    @JsonProperty(value = "cpf")
    private String cpf;
    @JsonProperty(value = "compras")
    private List<CompraDto> compras = new ArrayList<>();

    public Cliente toCliente() {
        return Cliente.builder()
            .nome(this.nome)
            .cpf(this.cpf)
            .build();
    }
}
