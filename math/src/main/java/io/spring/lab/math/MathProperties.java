package io.spring.lab.math;

import static java.math.RoundingMode.HALF_EVEN;

import java.math.BigDecimal;
import java.math.MathContext;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "math")
public class MathProperties {

	/** Precision for Math operations */
	private int precision = 18;

	/** Scale for Math operations */
	private int scale = 4;

	public MathContext getContext() {
		return new MathContext(precision, HALF_EVEN);
	}

	public BigDecimal bigDecimal(long unscaledVal) {
		return BigDecimal.valueOf(unscaledVal * (long) Math.pow(10, scale), scale);
	}
}
