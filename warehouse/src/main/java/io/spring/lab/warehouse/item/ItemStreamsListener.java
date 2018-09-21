package io.spring.lab.warehouse.item;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

import static io.spring.lab.warehouse.item.ItemStreamsBinding.STOCK_UPDATE;

@Component
@AllArgsConstructor
public class ItemStreamsListener {

    private final ItemService items;

    @StreamListener(STOCK_UPDATE)
    public void updateStock(ItemStockUpdate changes) {
        items.updateStock(changes);
    }
}
