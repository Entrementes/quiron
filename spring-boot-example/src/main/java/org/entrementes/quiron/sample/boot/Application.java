package org.entrementes.quiron.sample.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@ImportResource("classpath:spring-context.xml")
@ComponentScan
@EnableAutoConfiguration
public class Application {

	@Bean
	public javax.validation.Validator localValidatorFactoryBean() {
	   return new LocalValidatorFactoryBean();
	}
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
