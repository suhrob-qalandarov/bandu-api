package org.exp.banduapp.repository;

import org.exp.banduapp.models.entities.Booking;
import org.exp.banduapp.models.entities.Place;
import org.exp.banduapp.models.entities.User;
import org.exp.banduapp.models.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUserAndVisibilityTrueOrderByCreatedAtDesc(User user);

    @Query("""
        SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END
        FROM Booking b
        WHERE b.place = :place
          AND b.status IN :statuses
          AND b.startTime < :end
          AND b.endTime > :start
        """)
    boolean existsByPlaceAndStatusInAndTimeOverlap(
            @Param("place") Place place,
            @Param("statuses") Set<BookingStatus> statuses,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );
}
