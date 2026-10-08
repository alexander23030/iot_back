package com.iot.iot_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.iot.iot_back", "datosService", "datosController"})
@EnableJpaRepositories(basePackages = "datosRepository")
@EntityScan(basePackages = "datosEntity")
public class IotBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(IotBackApplication.class, args);
	}

}
