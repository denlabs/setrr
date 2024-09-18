package com.denlab.setrr.lead;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class LeadRoutesController {



    @GetMapping("/leads/{who}")
    public ResponseEntity<LeadRouteDto> hello(@PathVariable String who) {
        return ResponseEntity.ok(LeadRouteDto.builder().setterName(Objects.requireNonNullElse(who, "anonymous")).build());
    }

}
