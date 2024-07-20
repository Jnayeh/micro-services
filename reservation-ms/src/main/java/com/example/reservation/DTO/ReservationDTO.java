package com.example.reservation.DTO;

import com.example.reservation.Model.Reservation;
import com.example.reservation.Mapper.ReservationMapper;
import lombok.Builder;

@Builder
public record ReservationDTO(String reservationId, String nom, String prenom, String cin) {

  public static ReservationDTO mapFromEntity(Reservation reservation) {
    return ReservationMapper.INSTANCE.toDto(reservation);
  }

  public static Reservation mapToEntity(ReservationDTO reservationDTO) {
    return ReservationMapper.INSTANCE.toEntity(reservationDTO);
  }

}
