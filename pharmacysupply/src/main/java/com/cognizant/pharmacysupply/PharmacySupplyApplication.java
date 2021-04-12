package com.cognizant.pharmacysupply;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;




@SpringBootApplication
@EnableFeignClients
@Slf4j
@EnableSwagger2
public class PharmacySupplyApplication {
	public static void main(String[] args) {
		log.info("START");
		SpringApplication.run(PharmacySupplyApplication.class, args);
		log.info("END");
	}
	@Bean
	public Docket api() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.cognizant")).build()
				.apiInfo(apiInformation());
	}
	@SuppressWarnings("deprecation")
	private ApiInfo apiInformation() {
		
		return new ApiInfo("Pharamcy Medicine Supply Management System", "Pharamcy Supply", "1.0", "", "", "", "");
	}
}
