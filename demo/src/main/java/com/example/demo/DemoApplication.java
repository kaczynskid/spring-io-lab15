package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.Value;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}

@RestController
class GreetingController {

	@GetMapping("/greet/{name}")
	Greeting greet(@PathVariable("name") String name) {
		return new Greeting(String.format("Hello %s!", name));
	}
}

@Value
class Greeting {

	private String message;

}
