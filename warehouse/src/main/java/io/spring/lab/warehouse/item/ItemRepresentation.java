package io.spring.lab.warehouse.item;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemRepresentation {

	private String name;

	private int count;

	private BigDecimal price;

	static ItemRepresentation of(Item item) {
		return new ItemRepresentation(item.getName(), item.getCount(), item.getPrice());
	}

	Item asItem() {
		return new Item(null, name, count, price);
	}
}
