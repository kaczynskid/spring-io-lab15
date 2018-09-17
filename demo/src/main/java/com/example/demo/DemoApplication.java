package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
//		SpringApplication.run(DemoApplication.class, args);
		new DemoApplication()
				.configure(new SpringApplicationBuilder())
				.build().run(args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(DemoApplication.class);
	}
}

@RestController
class GreetingController {

	@Value("${greeting.template:Hi %s}")
	String template;

	@GetMapping("/greet/{name}")
	Greeting greet(@PathVariable("name") String name) {
		return new Greeting(String.format(template, name));
	}
}

@lombok.Value
class Greeting {

	private String message;
}
