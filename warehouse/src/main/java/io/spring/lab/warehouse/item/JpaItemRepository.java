package io.spring.lab.warehouse.item;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class JpaItemRepository implements ItemRepository {

    private final SpringDataItemRepository repository;

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

    interface SpringDataItemRepository extends JpaRepository<Item, Long> {

        Item findTopByOrderByPriceDesc();
    }
}
