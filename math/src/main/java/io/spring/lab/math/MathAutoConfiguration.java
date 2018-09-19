package io.spring.lab.math;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;

@ConditionalOnClass(ConfigurationPropertySource.class)
@ConditionalOnMissingBean(MathProperties.class)
@EnableConfigurationProperties(MathProperties.class)
public class MathAutoConfiguration {
}
