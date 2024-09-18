package com.denlab.setrr.lead;

import com.denlab.setrr.SetrrApplication;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = SetrrApplication.class)
class LeadRouteCreateCmdHandlerTest {

    @Autowired
    LeadRouteCreateCmdHandler handler;

    @Test
    void shouldHandler() {

        final var command = createRandomLeadRouteCreateCmd();
        final var leadRouteDto = handler.handle(command);

        assertThat(leadRouteDto).isNotNull();
        assertThat(leadRouteDto.getId()).isNotNull();

    }

    private LeadRouteCreateCmd createRandomLeadRouteCreateCmd() {
        return new LeadRouteCreateCmd("tester", "12a", "Anchor 12", "RockSteady", "blue");
    }

}