package com.is4302.healthcareblockchain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class HealthcareBlockchainApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthcareBlockchainApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Bean	
	public HttpHeaders JSONhttpHeaders(){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowCredentials(true).allowedHeaders("*")
                .allowedOrigins("*").exposedHeaders("Content-Type","Access-Control-Allow-Origin", "Access-Control-Allow-Headers","Authorization", "X-Requested-With", "requestId", "Set-Cookie","Access-Control-Allow-Credentials")
        		.allowedMethods("*");
            }
        };
    }		
	
}
