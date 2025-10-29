package org.exp.banduapp.repository;

import org.exp.banduapp.models.entities.Booking;
import org.exp.banduapp.models.entities.Place;
import org.exp.banduapp.models.entities.User;
import org.exp.banduapp.models.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query("""
        SELECT b.startTime, b.endTime
        FROM Booking b
        WHERE b.place = :place
          AND b.status IN ('PENDING', 'CONFIRMED')
          AND b.visibility = true
        ORDER BY b.startTime
        """)
    List<Object[]> findBookedTimeSlotsByPlace(@Param("place") Place place);

    @Modifying
    @Query(value = """
    UPDATE bookings
    SET status = 'CANCELLED'
    WHERE id = :bookingId
      AND user_id = :userId
      AND visibility = true
      AND status IN ('PENDING', 'CONFIRMED')
    RETURNING *
    """, nativeQuery = true)
    List<Booking> cancelAndReturnBooking(
            @Param("bookingId") Long bookingId,
            @Param("userId") Long userId
    );

    @Modifying
    @Query(value = """
    UPDATE bookings
    SET status = 'CONFIRMED'
    WHERE id = :bookingId
      AND visibility = true
      AND status IN ('PENDING')
    RETURNING *
    """, nativeQuery = true)
    List<Booking> confirmAndReturnBooking(@Param("bookingId") Long bookingId);
}
