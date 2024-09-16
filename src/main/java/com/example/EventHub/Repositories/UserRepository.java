package com.example.EventHub.Repositories;

import com.example.EventHub.Models.Domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM USER WHERE CUSTOMER_ID = ?1", nativeQuery = true)
    User getUserByUserId(String userId);
    Optional<User> findByUsername(String username);

    @Query("FROM User WHERE username = :username")
    User findByUsername2(String username);
}
