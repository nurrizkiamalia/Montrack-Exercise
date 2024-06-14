package com.amalia.demoJPA;

import com.amalia.demoJPA.config.RsaKeyConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyConfigProperties.class)
public class DemoJpaApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoJpaApplication.class, args);
	}
}