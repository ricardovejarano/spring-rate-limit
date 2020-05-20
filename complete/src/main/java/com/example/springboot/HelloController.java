package com.example.springboot;


import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import io.github.bucket4j.Bucket4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Duration;


@RestController
public class HelloController {

	private final Bucket bucket;

	public HelloController() {
		long capacity = 10;
		Refill refill = Refill.greedy(10, Duration.ofMinutes(1));
		Bandwidth limit = Bandwidth.classic(capacity, refill);
		this.bucket = Bucket4j.builder().addLimit(limit).build();

		// OR
		// Bandwidth limit = Bandwidth.simple(10, Duration.ofMinutes(1));
	}


	@RequestMapping("/")
	public String index() {
		if (this.bucket.tryConsume(1)) {
			return "Greetings from Spring Boot!";
		}
		return "No se puede";
	}

	@GetMapping("/simple")
	public ResponseEntity<String> getSimple() {
		return ResponseEntity.ok("Hi!");
	}

	@GetMapping("/advanced")
	public ResponseEntity<String> getAdvanced() {
		return ResponseEntity.ok("Hello, how you doing?");
	}

}
