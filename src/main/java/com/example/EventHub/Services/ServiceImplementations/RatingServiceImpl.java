package com.example.EventHub.Services.ServiceImplementations;


import com.example.EventHub.Models.Domains.EventOrganizer;
import com.example.EventHub.Models.Domains.Ratings;
import com.example.EventHub.Models.Domains.User;
import com.example.EventHub.Models.Dtos.RatingsDto;
import com.example.EventHub.Models.Dtos.UserDto;
import com.example.EventHub.Models.Dtos.UserResponseDto;
import com.example.EventHub.Repositories.RatingRepository;
import com.example.EventHub.Repositories.UserRepository;
import com.example.EventHub.Services.ServiceInterfaces.IRatingService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RatingServiceImpl implements IRatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;

    }

    @Autowired
    private ModelMapper modelMapper;


    public RatingsDto saveRating(RatingsDto ratingsDto){
        Ratings ratings = modelMapper.map(ratingsDto, Ratings.class);
        ratingRepository.save(ratings);
        return ratingsDto;

    }

    public List<RatingsDto> getAllRatings(){
        List<Ratings> ratings = ratingRepository.findAll();
        return ratings.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private RatingsDto convertToDto(Ratings ratings) {
        return new RatingsDto(
                ratings.getRatingId(),
                ratings.getComment(),
                ratings.getStars(),
                ratings.getUsername()
        );
    }




    public UserDto getUserByUsername(String username){
        User user = this.userRepository.getUserByUsername(username);
        return (UserDto)this.modelMapper.map(user, UserDto.class);
    }
}
