package com.example.reservation.services;

import com.example.reservation.DTO.ReservationDTO;
import com.example.reservation.Model.Reservation;
import com.example.reservation.Repositorys.ReservationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class ReservationServiceImpl implements IReservationService {
  private static final String TOPIC = "reservation-event";
  private ReservationRepository repository;
  private KafkaTemplate<String, String> kafkaTemplate;

  public List<ReservationDTO> findAll() {
    return repository.findAll().stream()
        .map(ReservationDTO::mapFromEntity)
        .collect(Collectors.toList());
  }

  public ReservationDTO getById(String id) {
    Reservation reservation = repository.findById(id).orElse(null);
    return ReservationDTO.mapFromEntity(reservation);
  }

  public ReservationDTO save(ReservationDTO reservationDTO) {
    Reservation reservation = ReservationDTO.mapToEntity(reservationDTO);
    reservation = repository.save(reservation);
    return ReservationDTO.mapFromEntity(reservation);
  }

  public void deleteById(String id) {
    repository.deleteById(id);
    reservationProducer(id);
  }

  public void reservationProducer(String id) {
    kafkaTemplate.send(TOPIC, id);
    log.info("Produced event: {}", id);
  }
}

