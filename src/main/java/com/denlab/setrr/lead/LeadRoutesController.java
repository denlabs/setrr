package com.denlab.setrr.lead;

import com.denlab.setrr.mediator.Mediator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class LeadRoutesController {

    private final Mediator mediator;



    @GetMapping("/leads/{who}")
    public ResponseEntity<LeadRouteDto> hello(@PathVariable String who) {
        return ResponseEntity.ok(LeadRouteDto.builder().setterName(Objects.requireNonNullElse(who, "anonymous")).build());
    }

    @PostMapping("/leads/")
    public ResponseEntity<LeadRouteDto> save(@RequestBody LeadRouteCreateCmd cmd) {
        return ResponseEntity.ok(mediator.send(cmd));
    }

}
