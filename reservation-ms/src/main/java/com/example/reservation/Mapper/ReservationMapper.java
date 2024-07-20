package com.example.reservation.Mapper;

import com.example.reservation.DTO.ReservationDTO;
import com.example.reservation.Model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
  ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

  @Mapping(target = "reservationId", source = "id")
  ReservationDTO toDto(Reservation stock);

  @Mapping(target = "id", source = "reservationId")
  Reservation toEntity(ReservationDTO reservationDTO);
}
