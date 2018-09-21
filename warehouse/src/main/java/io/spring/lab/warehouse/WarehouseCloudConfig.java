package io.spring.lab.warehouse;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

import io.spring.lab.warehouse.item.ItemStreamsBinding;

@Configuration
@EnableBinding(ItemStreamsBinding.class)
public class WarehouseCloudConfig {

}
