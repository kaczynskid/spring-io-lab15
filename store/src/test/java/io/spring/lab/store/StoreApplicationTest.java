package io.spring.lab.store;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import io.spring.lab.store.item.ItemsClient;
import io.spring.lab.store.special.SpecialClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class StoreApplicationTest extends SpringTestBase {

	@MockBean
	ItemsClient items;

	@MockBean
	SpecialClient specials;

	@Autowired
	ApplicationContext applicationContext;

	@Test
	public void contextLoads() {
		assertThat(applicationContext).isNotNull();
	}
}
