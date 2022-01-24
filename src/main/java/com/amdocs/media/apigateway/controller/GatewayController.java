package com.amdocs.media.apigateway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.amdocs.media.gateway.dto.Profile;

import reactor.core.publisher.Mono;

@RestController
public class GatewayController {

	private static final Logger logger = LoggerFactory.getLogger(GatewayController.class);

	
	@Autowired
	@Qualifier("KafkaServiceWebClient")
	WebClient webClient;
	
	@Autowired
	@Qualifier("ProfileServiceWebClient")
	WebClient profileWebClient;
	
	
	  @PutMapping("/profile")
		public ResponseEntity<Profile> kafkaPush(@RequestBody Profile profile) {

		  	logger.info("profile kafka push service called");
			profile = webClient.put().uri("kafka/profile/put").body(Mono.just(profile), Profile.class).retrieve()
					.bodyToMono(Profile.class).block();
			return new ResponseEntity<>(profile,HttpStatus.OK);

		}

	  @PostMapping("/profile")
		public ResponseEntity<Profile> saveProfile(@RequestBody Profile profile) {
		  	logger.info("profile save service called");

		  profile =  profileWebClient.post().uri("/saveProfile").body(Mono.just(profile), Profile.class).retrieve()
					.bodyToMono(Profile.class).block();
			return new ResponseEntity<>(profile,HttpStatus.OK);

		}
	  @DeleteMapping("/profile")
		public ResponseEntity<Profile> deleteProfile(@RequestBody Profile profile) {
		  	logger.info("profile kafka delete service called");

		  profile = profileWebClient.post().uri("/saveProfile").body(Mono.just(profile), Profile.class).retrieve()
					.bodyToMono(Profile.class).block();
		  
			return new ResponseEntity<>(profile,HttpStatus.OK);


		}

}
