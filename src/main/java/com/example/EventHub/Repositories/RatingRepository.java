package com.example.EventHub.Repositories;

import com.example.EventHub.Models.Domains.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Ratings, Long> {
}
