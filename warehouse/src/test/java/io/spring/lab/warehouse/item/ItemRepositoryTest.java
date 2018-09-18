package io.spring.lab.warehouse.item;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import io.spring.lab.warehouse.PersistenceConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.context.annotation.FilterType.ANNOTATION;
import static org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE;

@RunWith(SpringRunner.class)
@DataJpaTest(includeFilters = {
        @ComponentScan.Filter(type = ANNOTATION, classes = Repository.class),
        @ComponentScan.Filter(type = ASSIGNABLE_TYPE, classes = PersistenceConfiguration.class)
})
@AutoConfigureTestDatabase
@ActiveProfiles("test")
//@TestPropertySource(properties = {
//        "spring.jpa.show-sql=true"
//})
public class ItemRepositoryTest {

    @Autowired
    ItemRepository items;

    @Test
    public void shouldFindGivenItem() {
        Optional<Item> item = items.findOne(1L);

        assertThat(item).isPresent();
    }

    @Test
    public void shouldFindMostExpensiveItem() {
        Item item = items.findMostExpensive();

        assertThat(item.getId()).isEqualTo(1L);
        assertThat(item.getPrice()).isEqualTo(BigDecimal.valueOf(4000, 2));
    }
}
