package com.denlab.setrr.lead;

import com.denlab.setrr.mediator.RequestHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Component
public class LeadRouteBulkCreateCmdHandler implements RequestHandler<LeadRouteBulkCreateCmd, List<LeadRouteDto>> {

    private LeadRouteRepository lrp;

//    private KafkaTemplate kafkaTemplate;

    private RestClient restClient;


    public LeadRouteDto handle(final LeadRouteCreateCmd command) {
        final var leadRoute = lrp.save(LeadRoute.createLeadRoute(command));

        // tood pass events via kafka;
//        kafkaTemplate.send("my-topic", leadRoute.toString());

        return leadRoute.toDto();
    }

    @Override
    public List<LeadRouteDto> handle(final LeadRouteBulkCreateCmd command) {

        final String fileUrl = "https://raw.githubusercontent.com/denlabs/setrr/main/README.md";
        final String fileContent =  restClient.get()
                .uri(fileUrl)
                .retrieve()
                .body(String.class);



        // convert the file to a list of LeadRoute
        // save the list of LeadRoute
        // return the list of LeadRouteDto
        final var leadRoutes = fileContent.lines().skip(1)
                .parallel()
                .map(line -> LeadRoute.create(line.split(",")))
//                .collect(List::of, (BiConsumer<List<LeadRoute>, LeadRoute>)
//                        (objects, leadRoute) -> objects.add(lrp.save(leadRoute)), List::addAll);
                .collect(ArrayList<LeadRoute>::new, (list, leadRoute) -> list.add(lrp.save(leadRoute)), ArrayList::addAll);
        return leadRoutes.stream().map(LeadRoute::toDto).toList();
    }
}
