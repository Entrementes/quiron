package org.entrementes.quiron.sample.crud;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.entrementes.quiron.component.JsonCatalog;
import org.entrementes.quiron.component.QuironExpressionLanguage;
import org.entrementes.quiron.configuration.QuironConfiguration;
import org.entrementes.quiron.configuration.QuironConfigurationBuilder;
import org.entrementes.quiron.configuration.QuironHttpConfiguration;
import org.entrementes.quiron.filter.QuironControlMessageFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@ImportResource("classpath:spring-context.xml")
@ComponentScan
@EnableAutoConfiguration
public class CrudApplication extends SpringBootServletInitializer {

	private QuironConfiguration configuration = new QuironConfigurationBuilder().buildDefault();
	
	@Bean
	public QuironHttpConfiguration quironHttpConfiguration(){
		return this.configuration.getHttpConfiguration();
	}
	
	@Bean
	public QuironExpressionLanguage quironExpressionLanguage(){
		return this.configuration.getExpressionLanguage();
	}
	
	@Bean
	public JsonCatalog jsonCatalog(){
		return this.configuration.getJsonCatalog();
	}
	
	//@Bean
    public FilterRegistrationBean quironControlMessageFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new QuironControlMessageFilter(this.configuration.getHttpConfiguration()));
        registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
        registration.addUrlPatterns("/*");
        return registration;
    }
	
	@Bean
	public javax.validation.Validator localValidatorFactoryBean() {
	   return new LocalValidatorFactoryBean();
	}
	
    public static void main(String[] args) {
    	ConfigurableApplicationContext context = SpringApplication.run(CrudApplication.class, args);
        context.getBean(BusAdapter.class).contextInitialized();
    }
}
