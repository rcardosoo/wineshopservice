package com.wineshopservice.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wineshopservice.domain.Vinho;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class VinhoDto {
    @JsonProperty(value = "codigo")
    private Long codigo;
    @JsonProperty(value = "tipo_vinho")
    private String tipoVinho;
    @JsonProperty(value = "preco")
    private BigDecimal preco;
    @JsonProperty(value = "safra")
    private String safra;
    @JsonProperty(value = "ano_compra")
    private Integer anoCompra;

    public Vinho toVinho() {
        return Vinho.builder()
            .codigo(this.codigo)
            .tipoVinho(this.tipoVinho)
            .preco(this.preco)
            .safra(this.safra)
            .anoCompra(this.anoCompra)
            .build();
    }
}
