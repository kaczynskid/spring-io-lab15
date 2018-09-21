package io.spring.lab.store.item;

import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import lombok.AllArgsConstructor;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@Component
@AllArgsConstructor
@ConditionalOnProperty(value = "feign.client.enabled", havingValue = "true", matchIfMissing = true)
public class DeclarativeItemsClient implements ItemsClient {

    @FeignClient(name = "warehouse", path = "/items")
    interface FeignItemsClient {

        @GetMapping
        List<ItemRepresentation> findAll();

        @GetMapping("/{id}")
        ItemRepresentation findOne(@PathVariable("id") long id, @RequestHeader("Accept") String accept);

        @PutMapping("/{id}/stock")
        void updateStock(@PathVariable("id")long id, @RequestBody ItemStockUpdate changes);
    }

    private FeignItemsClient items;

    @Override
    public List<ItemRepresentation> findAll() {
        return items.findAll();
    }

    @Override
    public ItemRepresentation findOne(long id) {
        return items.findOne(id, APPLICATION_JSON_UTF8_VALUE);
    }

    @Override
    public void updateStock(ItemStockUpdate changes) {
        items.updateStock(changes.getId(), changes);
    }
}
