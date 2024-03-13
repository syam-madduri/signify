package com.signify.assignment;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.signify.assignment.entity.Review;
import com.signify.assignment.service.ReviewService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class AssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
	}



	@Bean
	CommandLineRunner runner(ReviewService reviewsService){
		return args -> {
			// read alexa.json
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Review>> typeReference = new TypeReference<List<Review>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/alexa.json");
			try {
				List<Review> reviews = mapper.readValue(inputStream,typeReference);
				reviews.forEach(reviewsService::save);
				System.out.println("Reviews Saved!");
			} catch (IOException e){
				System.out.println("Unable to save Reviews: " + e.getMessage());
			}
		};
	}
}
