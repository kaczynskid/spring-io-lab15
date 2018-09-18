package io.spring.lab.warehouse.item;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;

import static io.spring.lab.warehouse.item.QItem.item;

@Repository
@AllArgsConstructor
public class JpaItemRepository implements ItemRepository {

    private final SpringDataItemRepository repository;

    @Override
    public boolean isEmpty() {
        return repository.count() == 0;
    }

    @Override
    public Optional<Item> findOne(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Item> findAll() {
        return repository.findAll();
    }

    @Override
    public Item save(Item item) {
        return repository.save(item);
    }

    @Override
    public Item findMostExpensive() {
        return repository.findTopByOrderByPriceDesc();
    }

    @Override
    public List<Item> findByNamePrefix(String prefix) {
        //return repository.findByNamePrefix(prefix);
        return repository.findAll(item.name.startsWith(prefix));
    }

    interface SpringDataItemRepository extends
            JpaRepository<Item, Long>,
            QuerydslPredicateExecutor<Item> {

        Item findTopByOrderByPriceDesc();

        @Query("from Item where name like :prefix%")
        List<Item> findByNamePrefix(@Param("prefix") String namePrefix);

        @Override
        List<Item> findAll(Predicate predicate);
    }
}
