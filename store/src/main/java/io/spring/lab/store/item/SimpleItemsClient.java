package io.spring.lab.store.item;

import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SimpleItemsClient implements ItemsClient {

    private RestTemplate rest;

    private Environment env;

    @Override
    public List<ItemRepresentation> findAll() {
        return null;
    }

    @Override
    public ItemRepresentation findOne(long id) {
        String baseUri = env.getProperty("warehouse.host-port");
        return rest.getForObject("http://" + baseUri + "/items/{id}", ItemRepresentation.class, id);
    }

    @Override
    public void updateStock(ItemStockUpdate changes) {

    }
}
