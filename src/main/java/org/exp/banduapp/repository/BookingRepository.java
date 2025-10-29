package org.exp.banduapp.repository;

import org.exp.banduapp.models.entities.Booking;
import org.exp.banduapp.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUserAndVisibilityTrueOrderByCreatedAtDesc(User user);
}
