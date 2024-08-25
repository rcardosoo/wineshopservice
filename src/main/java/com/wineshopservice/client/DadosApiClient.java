package com.wineshopservice.client;

import com.wineshopservice.client.dto.ClienteDto;
import com.wineshopservice.client.dto.VinhoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "DadosApiClient", url = "${feign.client.config.dados-api.url}")
public interface DadosApiClient {

    @GetMapping("/produtos-mnboX5IPl6VgG390FECTKqHsD9SkLS.json")
    ResponseEntity<List<VinhoDto>> getVinhos();

    @GetMapping("/clientes-Vz1U6aR3GTsjb3W8BRJhcNKmA81pVh.json")
    ResponseEntity<List<ClienteDto>> getClientes();
}
