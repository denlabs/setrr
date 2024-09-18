package com.denlab.setrr.lead;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "routes")
public class LeadRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String setterName;

    private String proposedGrade;

    private String anchorName;

    private String setType;

    private String color;

    public static LeadRoute createLeadRoute(LeadRouteCreateCmd createCmd) {
        return LeadRoute.builder()
                .setterName(createCmd.getSetterName())
                .proposedGrade(createCmd.getProposedGrade())
                .anchorName(createCmd.getAnchorName())
                .setType(createCmd.getSetType())
                .color(createCmd.getColor())
                .build();
    }

    public LeadRouteDto andConvert() {
        return LeadRouteDto.builder()
                .id(id)
                .setterName(setterName)
                .proposedGrade(proposedGrade)
                .anchorName(anchorName)
                .setType(setType)
                .build();
    }
}
