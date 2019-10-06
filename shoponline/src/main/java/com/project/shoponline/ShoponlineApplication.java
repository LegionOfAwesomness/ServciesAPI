package com.project.shoponline;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.client.HttpClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.project.shoponline.controller.ConsumerProfileController;
import com.project.shoponline.model.module1.Consumer;

@SpringBootApplication
@ComponentScan("com.project.shoponline")
public class ShoponlineApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ShoponlineApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ShoponlineApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(ConsumerProfileController consumerProfileController) {
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
//			Car car = objectMapper.readValue(new File("target/json_car.json"), Car.class);
//			TypeReference<Consumer> typeReference = new TypeReference<Consumer>() {
//			};
//			InputStream inputStream = TypeReference.class.getResourceAsStream("src/main/resources/json/consumer.json");
			try {
//				Consumer consumer = mapper.readValue(inputStream, typeReference);
				Consumer consumer = mapper.readValue(new File("src/main/resources/json/consumer.json"), Consumer.class);
				System.out.println("Consumer read!");
				consumerProfileController.populateConsumerProfileOnStartup(consumer);
				System.out.println("Consumer Saved!");
			} catch (IOException e) {
				System.out.println("Unable to save consumer: " + e.getMessage());
			}
		};
	}
//	ggh
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	    mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
	    MappingJackson2HttpMessageConverter converter = 
	        new MappingJackson2HttpMessageConverter(mapper);
	    return converter;
	}
}
