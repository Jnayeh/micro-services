package com.example.activities.DTO;

import lombok.Builder;

@Builder
public record ActivityDTO(Long activityId, String designation, Integer duration, String reservationId,
                          ReservationDTO reservationDTO) {


}
