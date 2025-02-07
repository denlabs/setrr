package com.denlab.setrr.lead;

import com.denlab.setrr.mediator.RequestHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class LeadRouteCreateCmdHandler implements RequestHandler<LeadRouteCreateCmd, LeadRouteDto> {

    private LeadRouteRepository lrp;

//    private KafkaTemplate kafkaTemplate;

    @Override
    public LeadRouteDto handle(final LeadRouteCreateCmd command) {
        final var leadRoute = lrp.save(LeadRoute.createLeadRoute(command));

        // tood pass events via kafka;
//        kafkaTemplate.send("my-topic", leadRoute.toString());

        return leadRoute.toDto();
    }
}
