package com.denlab.setrr.lead;

import com.denlab.setrr.mediator.RequestHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class LeadRouteCreateCmdHandler implements RequestHandler<LeadRouteCreateCmd, LeadRouteDto> {

    private LeadRouteRepository lrp;

    @Override
    public LeadRouteDto handle(final LeadRouteCreateCmd command) {
        return lrp.save(LeadRoute.createLeadRoute(command)).andConvert();
    }
}
