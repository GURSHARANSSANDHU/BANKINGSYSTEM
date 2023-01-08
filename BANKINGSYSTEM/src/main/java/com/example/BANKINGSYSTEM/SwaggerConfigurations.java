package com.example.BANKINGSYSTEM;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//SWAGGER CONFIGURATIONS CLASS.......................
@Configuration
@EnableSwagger2
public class SwaggerConfigurations {

	// BEAN GENERATING FUNCTION .................
	@Bean
	public Docket getDocket() 
	{
		return  new Docket(DocumentationType.SWAGGER_2).groupName("FILE UPLOAD SERVICE").apiInfo(getInfo()).select().paths(PathSelectors.any()).build();
			}

	private ApiInfo getInfo() {
		// TODO Auto-generated method stub
		return new ApiInfo("FILE UPLOAD SERVICE", "THIS SERVICE CAN UPLOAD ANY FILE IN SIMPLE OR BASE 64 FORMAT TO A REMOTE LOCATION", "VERSION 0001", "http://localhost:1357/fileUploadService/uploadFile", new Contact("GURSHARAN S. SANDHU", "http://localhost:1357", "sandhugursharan70@gmail.com"), "LICENSE 1.2.3", "http://localhost:1357");
	}

}
