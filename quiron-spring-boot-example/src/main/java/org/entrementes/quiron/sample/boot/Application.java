package org.entrementes.quiron.sample.boot;

import org.entrementes.quiron.component.JsonCatalog;
import org.entrementes.quiron.component.QuironExpressionLanguage;
import org.entrementes.quiron.configuration.QuironConfiguration;
import org.entrementes.quiron.configuration.QuironConfigurationBuilder;
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

	private QuironConfiguration configuration = new QuironConfigurationBuilder().buildDefault();
	
	@Bean
	public QuironConfiguration quironConfiguration(){
		return this.configuration;
	}
	
	@Bean
	public QuironExpressionLanguage quironExpressionLanguage(){
		return this.configuration.getExpressionLanguage();
	}
	
	@Bean
	public JsonCatalog jsonCatalog(){
		return this.configuration.getJsonCatalog();
	}
	
	@Bean
	public javax.validation.Validator localValidatorFactoryBean() {
	   return new LocalValidatorFactoryBean();
	}
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
