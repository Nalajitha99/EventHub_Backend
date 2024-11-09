package com.example.EventHub.Services.ServiceInterfaces;

import com.example.EventHub.Models.Dtos.RatingsDto;

public interface IRatingService {
    RatingsDto saveRating(RatingsDto ratingsDto);
}
