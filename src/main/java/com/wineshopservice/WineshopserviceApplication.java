package com.wineshopservice;

import com.wineshopservice.service.ImportaDadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WineshopserviceApplication implements CommandLineRunner {

    @Autowired
    private ImportaDadosService importaDadosService;

	public static void main(String[] args) {
		SpringApplication.run(WineshopserviceApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        importaDadosService.sincronizar();
    }
}
