package com.example.activities.FeignClient;

import com.example.activities.DTO.ReservationDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "RESERVATION-MS", configuration = FeignConfig.class)
public interface ReservationClient {

  @GetMapping("/reservations/{id}")
  @CircuitBreaker(name = "reservation-ms", fallbackMethod = "fallbackgetRsvById")
  ReservationDTO getRsvById(@PathVariable("id") String id);


}
