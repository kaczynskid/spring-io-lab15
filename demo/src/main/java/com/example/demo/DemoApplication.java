package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ExitCodeExceptionMapper;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import static java.util.Optional.ofNullable;
import static org.springframework.core.NestedExceptionUtils.getMostSpecificCause;

@Slf4j
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

	@Bean
	ApplicationRunner init(Environment environment) {
		return (args) -> {
			log.info("### RUNNING ###");

			log.info("### TEMPLATE: {} ###",
					ofNullable(environment.getProperty("greeting.template"))
							.orElseThrow(GreetingTemplateMissing::new));
		};
	}

//	@Bean
//	ExitCodeExceptionMapper exitCodeExceptionMapper() {
//		return e -> {
//			Throwable cause = getMostSpecificCause(e);
//			if (cause instanceof GreetingTemplateMissing) {
//				return 11;
//			} else {
//				return 1;
//			}
//		};
//	}
}

class GreetingTemplateMissing extends RuntimeException implements ExitCodeGenerator {

	GreetingTemplateMissing() {
		super("Greeting template not defined!");
	}

	@Override
	public int getExitCode() {
		return 13;
	}
}

@RestController
class GreetingController {

	@Autowired
	Environment env;

	@GetMapping("/greet/{name}")
	Greeting greet(@PathVariable("name") String name) {
		String template = env.getProperty("greeting.template", "Hi %s");
		return new Greeting(String.format(template, name));
	}
}

@lombok.Value
class Greeting {

	private String message;
}
