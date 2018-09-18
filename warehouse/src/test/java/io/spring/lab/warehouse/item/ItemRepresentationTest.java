package io.spring.lab.warehouse.item;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@JsonTest
public class ItemRepresentationTest {

    @Autowired
    JacksonTester<ItemRepresentation> json;

    @Test
    public void shouldSerializeItemRepresentation() throws Exception {

    }

    @Test
    public void shouldDeserializeItemRepresentation() throws Exception {

    }

}
