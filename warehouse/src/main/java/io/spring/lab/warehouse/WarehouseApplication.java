package io.spring.lab.warehouse;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import io.spring.lab.warehouse.item.ItemRepository;
import lombok.AllArgsConstructor;

@SpringBootApplication
public class WarehouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarehouseApplication.class, args);
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
