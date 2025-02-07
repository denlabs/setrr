package com.denlab.setrr.lead;

import com.denlab.setrr.SetrrApplication;
import com.denlab.setrr.lead.events.LeadRouteCreatedMessageConsumer;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = SetrrApplication.class)
class LeadRouteCreateCmdHandlerTest {

    @Autowired
    LeadRouteCreateCmdHandler handler;

//    @SpyBean
//    LeadRouteCreatedMessageConsumer consumer;

    @Test
    void shouldHandler() {

//        doAnswer(invocation -> {
//            String message = invocation.getArgument(0);
//            System.out.println("Received message: " + message);
//            return null;
//
//        }).when(consumer).listen(any(String.class));

        final var command = createRandomLeadRouteCreateCmd();
        final var leadRouteDto = handler.handle(command);

        assertThat(leadRouteDto).isNotNull();
        assertThat(leadRouteDto.getId()).isNotNull();

//        verify(consumer).listen(any(String.class));
    }

    private LeadRouteCreateCmd createRandomLeadRouteCreateCmd() {
        return new LeadRouteCreateCmd("tester", "12a", "Anchor 12", "RockSteady", "blue");
    }

}