package io.spring.lab.store.special;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
@ConditionalOnProperty(value = "feign.client.enabled", havingValue = "true", matchIfMissing = true)
public class DeclarativeSpecialClient implements SpecialClient {

    @FeignClient(name = "marketing", path = "/specials")
    interface FeignSpecialClient {

        @PostMapping("/{itemId}/calculate")
        SpecialCalculation calculateFor(
                @PathVariable("itemId") long itemId,
                @RequestBody SpecialCalculationRequest request);
    }

    private FeignSpecialClient specials;

    @Override
    public SpecialCalculation calculateFor(long itemId, SpecialCalculationRequest request) {
        return specials.calculateFor(itemId, request);
    }
}
