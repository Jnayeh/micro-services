package com.example.activities.services;

import com.example.activities.DTO.ActivityDTO;
import com.example.activities.DTO.ReservationDTO;
import com.example.activities.FeignClient.ReservationClient;
import com.example.activities.Mappers.ActivityMapper;
import com.example.activities.Models.Activity;
import com.example.activities.Repositorys.ActivityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class ActiviyServiceImpl implements IActiviyService {
  private static final String RESERVATION_SERVICE_URL = "http://RESERVATION-MS/etudiants/";
  private ActivityRepository activityRepository;
  private ReservationClient reservationClient;
  private ActivityMapper activityMapper;
  private RestTemplate restTemplate;

  public ActivityDTO getById(Long id) {
    Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String token = jwt.getTokenValue();
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(token);
    HttpEntity<String> entity = new HttpEntity<>(headers);

    return activityRepository.findById(id).map(activity -> {
      ResponseEntity<ReservationDTO> response = restTemplate.exchange(
          RESERVATION_SERVICE_URL + activity.getReservationId(),
          HttpMethod.GET,
          entity,
          ReservationDTO.class
      );
      ReservationDTO reservationDTO = response.getBody();
      ActivityDTO activityDTO = activityMapper.toDto(activity);
      return new ActivityDTO(activityDTO.activityId(), activityDTO.designation(), activityDTO.duration(), activityDTO.reservationId(), reservationDTO);
    }).orElseThrow(() -> new IllegalArgumentException("Etudiant not found"));
  }

  public ActivityDTO save(ActivityDTO activityDTO) {
    ReservationDTO reservationDTO = reservationClient.getRsvById(activityDTO.reservationId());
    if (reservationDTO != null) {
      Activity activity = activityMapper.toEntity(activityDTO);
      activityRepository.save(activity);
      return new ActivityDTO(activityDTO.activityId(), activityDTO.designation(), activityDTO.duration(), activityDTO.reservationId(), reservationDTO);
    } else throw new IllegalArgumentException("Examen not found");
  }

  public List<ActivityDTO> findAll() {
    return activityRepository.findAll().stream()
        .map(activity -> {
          ActivityDTO activityDTO = activityMapper.toDto(activity);
          ReservationDTO reservationDTO = reservationClient.getRsvById(activityDTO.reservationId());
          return new ActivityDTO(activityDTO.activityId(), activityDTO.designation(), activityDTO.duration(), activityDTO.reservationId(), reservationDTO);
        })
        .collect(Collectors.toList());
  }

  public void deleteById(Long id) {
    activityRepository.deleteById(id);
  }

  @KafkaListener(topics = "reservation-event", groupId = "group_id")
  public void reservationListener(String id) {
    activityRepository.findAllByReservationId(id).forEach(
        activity -> deleteById(activity.getId())
    );
    log.info("Consumed event: {}", id);
  }

}

