package com.kristonmoney.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.kristonmoney.api.config.property.KristonmoneyApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(KristonmoneyApiProperty.class)
public class KristonmoneyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(KristonmoneyApiApplication.class, args);
	}

}
