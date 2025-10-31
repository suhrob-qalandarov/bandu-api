package org.exp.banduapp.repository;

import org.exp.banduapp.models.entities.RoadMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoadMapRepository extends JpaRepository<RoadMap,Long> {

    List<RoadMap> findAllByVisibility(Boolean visibility);
}
