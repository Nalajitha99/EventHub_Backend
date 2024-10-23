package com.example.EventHub.Models.Dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RatingsDto {

    private long ratingId;
    private String comment;
    private String stars;
    private String username;
}
