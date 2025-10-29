package org.exp.banduapp.models.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.exp.banduapp.models.base.BaseEntity;
import org.exp.banduapp.models.enums.PlaceStatus;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "places")
public class Place extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false)
    private BigDecimal pricePerHour;

    @Builder.Default
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PlaceStatus status = PlaceStatus.ACTIVE;

    @Builder.Default
    @Column(nullable = false)
    private boolean visibility = true;
}
