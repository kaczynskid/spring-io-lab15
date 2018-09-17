package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class DemoApplicationTests {

	@Autowired
	private ApplicationContext context;

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate rest;

	@Test
	public void contextLoads() {
		assertThat(context).isNotNull();
	}

	@Test
	public void shouldGreetWithLocalPort() {
		assertThat(port).isGreaterThan(0);

		String greeting = new RestTemplate()
				.getForObject(String.format("http://localhost:%s/greet/John", port), Greeting.class)
				.getMessage();

		assertThat(greeting).isEqualTo("Hello again John!");
	}

	@Test
	public void shouldGreetWithTestRestTemplate() {
		assertThat(rest).isNotNull();

		String greeting = rest
				.getForObject("/greet/{name}", Greeting.class, "John")
				.getMessage();

		assertThat(greeting).isEqualTo("Hello again John!");
	}
}
