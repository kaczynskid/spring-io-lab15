package io.spring.lab.store;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase
@TestPropertySource(properties = {
        "spring.cloud.bootstrap.enabled=false",
        "spring.profiles.active=test"
})
public abstract class SpringTestBase {
}
