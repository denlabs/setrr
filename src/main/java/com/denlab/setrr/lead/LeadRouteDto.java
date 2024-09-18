package com.denlab.setrr.lead;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LeadRouteDto {

    private Long id;
    private String setterName;
    private String proposedGrade;
    private String anchorName;
    private String setType;

}
