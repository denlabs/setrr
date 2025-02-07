package com.denlab.setrr.lead;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LeadRouteBulkCreateCmdHandlerTest {

    @Autowired
    LeadRouteBulkCreateCmdHandler handler;

    @Test
    void shouldLoad() {
        final var handle = handler.handle(new LeadRouteBulkCreateCmd());
        handle.stream().forEach(System.out::println);
    }

}