package io.spring.lab.store;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifier;

import io.spring.lab.store.basket.Basket;
import io.spring.lab.store.basket.BasketService;

import static io.spring.lab.store.item.ItemStreamsBinding.CHECKOUT_ITEM;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@SpringBootTest(webEnvironment = NONE)
public abstract class StoreContractTestBase extends StubRunnerTestBase {

    @Autowired
    MessageVerifier messaging;

    @Autowired
    BasketService baskets;

    @Before
    public void setUp() {
        // clear any remaining messages
        messaging.receive(CHECKOUT_ITEM, 100, TimeUnit.MILLISECONDS);
    }

    public void basketCheckout() {
        Basket basket = baskets.create();
        baskets.updateItem(basket.getId(), ITEM_ID, ITEM_REGULAR_COUNT);
        baskets.checkout(basket.getId());
    }
}
