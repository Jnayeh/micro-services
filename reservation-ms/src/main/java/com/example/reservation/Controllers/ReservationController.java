package com.example.reservation.Controllers;

import com.example.reservation.DTO.ReservationDTO;
import com.example.reservation.services.IReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@Slf4j
@RequiredArgsConstructor
@RefreshScope
public class ReservationController {
  private final IReservationService reservationService;

  @GetMapping
  public List<ReservationDTO> getAllRsvs() {
    return reservationService.findAll();
  }

  @GetMapping("/{id}")
  public ReservationDTO getRsvById(@PathVariable String id) {
    return reservationService.getById(id);
  }

  @PostMapping
  public ReservationDTO makeRsv(@RequestBody ReservationDTO reservationDTO) {
    return reservationService.save(reservationDTO);
  }

  @PutMapping("/{id}")
  public ReservationDTO updateRsv(@PathVariable String id, @RequestBody ReservationDTO reservationDTO) {
    return reservationService.save(reservationDTO);
  }

  @DeleteMapping("/{id}")
  public void deleteRsv(@PathVariable String id) {
    reservationService.deleteById(id);
  }

}
