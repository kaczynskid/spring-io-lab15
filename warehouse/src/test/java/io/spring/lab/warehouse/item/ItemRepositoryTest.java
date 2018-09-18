package io.spring.lab.warehouse.item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import io.spring.lab.warehouse.PersistenceConfiguration;
import io.spring.lab.warehouse.SpringTestBase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.context.annotation.FilterType.ANNOTATION;
import static org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE;

@DataJpaTest(includeFilters = {
        @ComponentScan.Filter(type = ANNOTATION, classes = Repository.class),
        @ComponentScan.Filter(type = ASSIGNABLE_TYPE, classes = PersistenceConfiguration.class)
})
//@TestPropertySource(properties = {
//        "spring.jpa.show-sql=true"
//})
public class ItemRepositoryTest extends SpringTestBase {

    @Autowired
    ItemRepository items;

    @Autowired
    TestEntityManager jpa;

    @Test
    public void shouldFindGivenItem() {
        Optional<Item> item = items.findOne(1L);

        assertThat(item).isPresent();
    }

    @Test
    public void shouldFindMostExpensiveItem() {
        Item item = items.findMostExpensive();

        assertThat(item.getId()).isEqualTo(1L);
        assertThat(item.getPrice()).isEqualTo(BigDecimal.valueOf(400000, 4));
    }

    @Test
    public void shouldFindByNamePrefix() {
        Item item = jpa.persistAndFlush(
                new Item(null, "Xero", 10, BigDecimal.valueOf(999.99)));

        List<Item> found = items.findByNamePrefix("X");

        assertThat(found).hasSize(1);
        assertThat(found).containsExactly(item);
    }
}
