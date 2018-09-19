package io.spring.lab.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import io.spring.lab.web.client.request.UriCustomizer;

import static org.springframework.web.util.UriComponentsBuilder.fromUri;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

	@Bean
	RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	UriCustomizer uriCustomizer(Environment env) {
		return uri -> {
			String serviceId = uri.getHost();
			String serviceHost = env.getProperty(serviceId + ".host", uri.getHost());
			Integer servicePort = env.getProperty(serviceId + ".port", Integer.class, uri.getPort());
			return fromUri(uri)
					.host(serviceHost)
					.port(servicePort)
					.build().toUri();
		};
	}
}
