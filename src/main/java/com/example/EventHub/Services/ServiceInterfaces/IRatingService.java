package com.example.EventHub.Services.ServiceInterfaces;

import com.example.EventHub.Models.Dtos.RatingsDto;

import java.util.List;

public interface IRatingService {
    RatingsDto saveRating(RatingsDto ratingsDto);
    List<RatingsDto> getAllRatings();
}
