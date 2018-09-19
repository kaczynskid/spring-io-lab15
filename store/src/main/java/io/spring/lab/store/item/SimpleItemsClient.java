package io.spring.lab.store.item;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;

import static java.util.Collections.singletonList;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

@Component
@AllArgsConstructor
public class SimpleItemsClient implements ItemsClient {

    private RestTemplate rest;

    @Override
    public List<ItemRepresentation> findAll() {
        return null;
    }

    @Override
    public ItemRepresentation findOne(long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(singletonList(APPLICATION_JSON_UTF8));
        HttpEntity<?> entity = new HttpEntity<>(headers);
        return rest.exchange("http://warehouse/items/{id}", GET, entity, ItemRepresentation.class, id)
                .getBody();
    }

    @Override
    public void updateStock(ItemStockUpdate changes) {

    }
}
