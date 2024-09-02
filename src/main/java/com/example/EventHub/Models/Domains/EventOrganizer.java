package com.example.EventHub.Models.Domains;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EventOrganizer {
    @Id
    private long organizerId;
    private long customerId;
    private String organization;

}
