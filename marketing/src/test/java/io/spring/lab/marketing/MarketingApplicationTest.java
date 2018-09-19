package io.spring.lab.marketing;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MarketingApplicationTest extends SpringTestBase {

	@Autowired
	ApplicationContext applicationContext;

	@Test
	public void contextLoads() {
		assertThat(applicationContext).isNotNull();
	}
}
