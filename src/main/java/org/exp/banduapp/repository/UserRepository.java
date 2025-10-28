package org.exp.banduapp.repository;

import org.exp.banduapp.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPhoneNumber(String phoneNumber);

    boolean existsUserByPhoneNumber(String phoneNumber);

    @Query(value = """
    SELECT u.*
    FROM users u
    WHERE u.phone_number = :phoneNumber
      AND u.otp_code = :otpCode
    """, nativeQuery = true)
    Optional<User> findByPhoneNumberAndOtpCode(
            @Param("phoneNumber") String phoneNumber,
            @Param("otpCode") String otpCode
    );

    @Modifying
    @Query("UPDATE User u SET u.visibility = NOT u.visibility WHERE u.id = :id")
    int toggleVisibilityById(@Param("id") Long id);
}
