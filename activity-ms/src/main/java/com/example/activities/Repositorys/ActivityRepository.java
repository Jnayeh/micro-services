package com.example.activities.Repositorys;

import com.example.activities.Models.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

  List<Activity> findAllByReservationId(String reservationId);
}
