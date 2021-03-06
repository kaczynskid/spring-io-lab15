package io.spring.lab.marketing;

import org.junit.runner.RunWith;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource(properties = {
        "spring.cloud.bootstrap.enabled=false",
        "spring.profiles.active=test"
})
public abstract class SpringTestBase {
}
