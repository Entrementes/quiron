package org.entrementes.quiron.sample.bus;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:spring-context.xml")
@ComponentScan
@EnableAutoConfiguration
public class BusApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(BusApplication.class, args);
    }
}
