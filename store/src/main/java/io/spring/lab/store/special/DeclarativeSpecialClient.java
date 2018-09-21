package io.spring.lab.store.special;

import java.math.BigDecimal;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.spring.lab.math.MathProperties;
import lombok.AllArgsConstructor;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;

@Component
@AllArgsConstructor
@ConditionalOnProperty(value = "feign.client.enabled", havingValue = "true", matchIfMissing = true)
public class DeclarativeSpecialClient implements SpecialClient {

    @FeignClient(name = "marketing", path = "/specials", fallback = FallbackSpecialClient.class)
    interface FeignSpecialClient {

        @PostMapping("/{itemId}/calculate")
        SpecialCalculation calculateFor(
                @PathVariable("itemId") long itemId,
                @RequestBody SpecialCalculationRequest request);
    }

    @Component
    @AllArgsConstructor
    static class FallbackSpecialClient implements FeignSpecialClient {

        private MathProperties math;

        @Override
        public SpecialCalculation calculateFor(long itemId, SpecialCalculationRequest request) {
            BigDecimal unitCount = math.bigDecimal(request.getUnitCount());
            BigDecimal regularPrice = request.getUnitPrice().multiply(unitCount, math.getContext());
            BigDecimal discountRate = ONE.divide(TEN, math.getContext());
            BigDecimal discount = regularPrice.multiply(discountRate, math.getContext());
            BigDecimal fallbackPrice = regularPrice.subtract(discount, math.getContext());
            return new SpecialCalculation("fallback", fallbackPrice);
        }
    }

    private FeignSpecialClient specials;

    @Override
    public SpecialCalculation calculateFor(long itemId, SpecialCalculationRequest request) {
        return specials.calculateFor(itemId, request);
    }
}
