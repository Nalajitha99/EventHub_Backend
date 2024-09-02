package com.example.EventHub.Repositories;

import com.example.EventHub.Models.Domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
