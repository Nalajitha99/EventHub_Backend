package com.example.EventHub.Models.Domains;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ratings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ratingId;
    private String comment;
    private String stars;
    private String username;

    public long getRatingId() {
        return ratingId;
    }

    public String getComment() {
        return comment;
    }

    public String getStars() {
        return stars;
    }

    public String getUsername() {
        return username;
    }
}
