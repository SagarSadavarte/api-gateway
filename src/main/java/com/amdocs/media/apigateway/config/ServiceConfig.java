package com.amdocs.media.apigateway.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ServiceConfig {

	@Bean("KafkaServiceWebClient")
	@LoadBalanced
	public WebClient getkafkaServiceWebClient() {
	        return WebClient.builder()
	        		.baseUrl("http://kafka-service/")
	                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	                .build();
	}
	
	@Bean("ProfileServiceWebClient")
	@LoadBalanced
	public WebClient getProfileServiceWebClient() {
		return WebClient
				.builder()
				.baseUrl("http://profile-service/")
				//.baseUrl("http://localhost:8082/profileService")
		        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}

}
