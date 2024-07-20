package com.example.activities.DTO;

import lombok.Builder;

@Builder
public record ReservationDTO(String reservationId, String nom, String prenom, String cin) {
}