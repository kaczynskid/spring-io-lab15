package io.spring.lab.warehouse

import io.spring.lab.warehouse.item.ItemRepository

import static io.spring.lab.warehouse.WarehousePersistenceConfig.testDataInitializer

class TestDataConfiguration {

    static void itemsTestData(ItemRepository items) {
        testDataInitializer(items).run(null)
    }
}
