package org.exp.banduapp.repository;

import org.exp.banduapp.models.entities.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    Optional<Place> findByIdAndVisibilityTrue(Long id);
}
