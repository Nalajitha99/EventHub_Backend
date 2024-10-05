package com.example.EventHub.Models.Dtos;

public class UserResponseDto {

    private String message;
    private String emailResponse;

    public String getMessage() {
        return this.message;
    }
    public String getEmailResponse() {
        return this.emailResponse;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public void setEmailResponse(String emailResponse) {
        this.emailResponse = emailResponse;
    }

    public UserResponseDto(String message, String emailResponse) {
        this.message = message;
        this.emailResponse = emailResponse;
    }

    public UserResponseDto() {
    }
}
