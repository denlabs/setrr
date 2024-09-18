package com.denlab.setrr.lead;

import com.denlab.setrr.mediator.Request;
import lombok.Value;

@Value
public class LeadRouteCreateCmd implements Request<LeadRouteDto> {

    String setterName;
    String proposedGrade;
    String anchorName;
    String setType;
    String color;

}
