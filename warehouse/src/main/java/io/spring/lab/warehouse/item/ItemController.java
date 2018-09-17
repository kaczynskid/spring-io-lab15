package io.spring.lab.warehouse.item;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/items")
@AllArgsConstructor
class ItemController {

    private ItemService items;

    @GetMapping
    List<ItemRepresentation> findAll() {
        return items.findAll().stream()
                .map(ItemRepresentation::of)
                .collect(toList());
    }

    @GetMapping("/{id}")
    ItemRepresentation findOne(@PathVariable("id") long id) {
        return ItemRepresentation.of(items.findOne(id));
    }

    @PostMapping
    void create(@RequestBody ItemRepresentation request) {
        items.create(request.asItem());
    }
}
