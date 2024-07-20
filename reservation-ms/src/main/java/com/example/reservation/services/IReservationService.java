package com.example.reservation.services;


import com.example.reservation.DTO.ReservationDTO;

import java.util.List;

public interface IReservationService {
  List<ReservationDTO> findAll();

  ReservationDTO getById(String id);

  ReservationDTO save(ReservationDTO reservationDTO);

  void deleteById(String id);

  void reservationProducer(String id);
}

