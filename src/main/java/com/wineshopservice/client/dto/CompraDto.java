package com.wineshopservice.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CompraDto {
    @JsonProperty(value = "codigo")
    private String codigo;
    @JsonProperty(value = "quantidade")
    private Long quantidade;
}
