package com.example.springboot;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

	@RequestMapping("/")
	public String index() {
			return "Greetings from Spring Boot!";
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
