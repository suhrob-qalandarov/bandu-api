package org.exp.banduapp.models.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.exp.banduapp.models.base.BaseEntity;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "road_map_tasks")
public class RoadMapTask extends BaseEntity {

    @Column(nullable = false, columnDefinition = "TEXT")
    private String task;

    @Builder.Default
    @Column(nullable = false)
    private boolean completed = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "road_map_id", nullable = false)
    private RoadMap roadMap;

    @Builder.Default
    private Boolean visibility = true;
}