package com.denlab.setrr.lead;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "routes")
public class LeadRoute extends DomainEntity<LeadRoute>{

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

    public static LeadRoute create(final String[] array) {
        // id,setterName,proposedGrade,anchorName,setType
        return LeadRoute.builder()
                .id(Long.parseLong(array[0]))
                .setterName(array[1])
                .proposedGrade(array[2])
                .anchorName(array[3])
                .setType(array[4])
                .build();
    }

    public  LeadRouteDto toDto() {
        return LeadRouteDto.builder()
                .id(id)
                .setterName(setterName)
                .proposedGrade(proposedGrade)
                .anchorName(anchorName)
                .setType(setType)
                .build();
    }
}
