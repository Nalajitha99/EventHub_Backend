package com.example.EventHub.Repositories;

import com.example.EventHub.Models.Domains.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM USER WHERE CUSTOMER_ID = ?1", nativeQuery = true)
    User getUserByUserId(String userId);
    Optional<User> findByUsername(String username);

    @Query("FROM User WHERE username = :username")
    User findByUsername2(String username);

    User findByEmail(String email);

    @Modifying
    @Query("UPDATE User SET verified = true WHERE email = :email")
    @Transactional
    void verifyByEmail(String email);


    @Modifying
    @Transactional
    void deleteByCreatedDateBeforeAndVerifiedFalse(LocalDateTime oneMinuteAgo);

    @Query(value = "SELECT * FROM USER WHERE username = ?1", nativeQuery = true)
    User getUserByUsername(String username);
}
