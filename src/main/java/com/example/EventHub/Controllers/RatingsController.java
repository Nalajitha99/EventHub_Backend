package com.example.EventHub.Controllers;


import com.example.EventHub.Models.Dtos.RatingsDto;
import com.example.EventHub.Services.ServiceInterfaces.IRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "api/v1/rating")
public class RatingsController {

    @Autowired
    private IRatingService ratingService;

    @Autowired
    public RatingsController(IRatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/saveRating")
    public ResponseEntity<?> saveRating(@RequestBody RatingsDto ratingsDto){
        try{
            return ResponseEntity.ok(ratingService.saveRating(ratingsDto));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getAllRatings")
    public ResponseEntity<List<RatingsDto>> getAllRatings(){
        List<RatingsDto> ratings = ratingService.getAllRatings();
        return ResponseEntity.ok(ratings);
    }
}
