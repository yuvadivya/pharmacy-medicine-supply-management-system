package com.cognizant.medicalrepresentativeschedule;

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

@Slf4j
@EnableFeignClients
@SpringBootApplication
@EnableSwagger2
public class MedRepScheduleServiceApplication {
	
	public static void main(String[] args) {
		log.info("Start");
		
		SpringApplication.run(MedRepScheduleServiceApplication.class, args);
		log.info("End");
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
		
		return new ApiInfo("Pharamcy Medicine Supply Management System", "Medicine Represntative schedule", "1.0", "", "", "", "");
	}

}
