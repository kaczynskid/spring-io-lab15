package io.spring.lab.warehouse;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import io.spring.lab.warehouse.item.ItemRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class WarehouseApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(WarehouseApplication.class);
		app.setDefaultProperties(defaultProperties());
		app.run(args);
	}

	private static Map<String, Object> defaultProperties() {
		Map<String, Object> props = new HashMap<>();
		props.put("info.instanceId", UUID.randomUUID().toString().replaceAll("-", ""));
		return props;
	}

	@Bean
	ApplicationRunner instanceIdLogger(Environment environment) {
		return args -> {
			log.info("instanceId: {}", environment.getProperty("info.instanceId", "UNDEFINED"));
		};
	}
}

//@Component
class RandomHealthIndicator implements HealthIndicator {

	private static final Random RANDOM = new Random();

	@Override
	public Health health() {
		return Health.status(RANDOM.nextBoolean() ? Status.UP : Status.DOWN)
				.withDetail("someNumber", RANDOM.nextLong())
				.build();
	}
}

@Component
@AllArgsConstructor
class InstanceInfoContributor implements InfoContributor {

	private ItemRepository items;

	@Override
	public void contribute(Info.Builder builder) {
		Map<String, Object> info = new HashMap<>();
		info.put("count", items.count());
		builder.withDetail("items", info);
	}
}
