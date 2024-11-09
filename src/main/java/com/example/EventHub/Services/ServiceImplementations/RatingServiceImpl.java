package com.example.EventHub.Services.ServiceImplementations;


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

@Service
@Transactional
public class RatingServiceImpl implements IRatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository, JavaMailSender javaMailSender) {
        this.ratingRepository = ratingRepository;
        this.javaMailSender = javaMailSender;
    }

    @Autowired
    private ModelMapper modelMapper;

    private final JavaMailSender javaMailSender;

    public RatingsDto saveRating(RatingsDto ratingsDto){

        ratingRepository.save(modelMapper.map(ratingsDto, Ratings.class));

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setMessage("Rating created successfully");
        String userName = ratingsDto.getUsername();
        UserDto userDto = new UserDto();
        userDto = getUserByUsername(userName);
        UserResponseDto emailResponse=sendEmail(userDto.getEmail());
        userResponseDto.setEmailResponse(emailResponse.getEmailResponse());
        return ratingsDto;
    }

    public UserResponseDto sendEmail(String email){
        UserResponseDto userDTO = new UserResponseDto();
        try{
            SimpleMailMessage message=new SimpleMailMessage();
            message.setSubject("Hello User");
            message.setTo(email);
            message.setText("Thank You for Rating Us.\n Have a nice day!"+" ");
            message.setFrom("hubevenlk@gmail.com");


            javaMailSender.send(message);
            userDTO.setEmailResponse("Thanking email send Successfully to" +" "+ email);

        }
        catch (Exception e){
            throw new IllegalArgumentException("Email not sent"+e);
        }
        return userDTO;
    }

    public UserDto getUserByUsername(String username){
        User user = this.userRepository.getUserByUsername(username);
        return (UserDto)this.modelMapper.map(user, UserDto.class);
    }
}
