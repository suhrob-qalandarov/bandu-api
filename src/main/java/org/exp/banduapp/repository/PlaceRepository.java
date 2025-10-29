package org.exp.banduapp.repository;

import org.exp.banduapp.models.entities.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    Optional<Place> findByIdAndVisibilityTrue(Long id);

    List<Place> findAllByVisibility(boolean visibility);

    @Modifying
    @Query(value = """
    UPDATE places
    SET status = :status
    WHERE id = :placeId
    RETURNING *
    """, nativeQuery = true)
    List<Place> updateStatusAndReturnEntity(
            @Param("placeId") Long placeId,
            @Param("status") String status
    );
}
