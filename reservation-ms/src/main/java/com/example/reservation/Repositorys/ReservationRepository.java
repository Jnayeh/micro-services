package com.example.reservation.Repositorys;

import com.example.reservation.Model.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ReservationRepository extends MongoRepository<Reservation, String> {
}
