package com.example.activities.services;


import com.example.activities.DTO.ActivityDTO;

import java.util.List;

public interface IActiviyService {
  ActivityDTO getById(Long id);

  ActivityDTO save(ActivityDTO activityDTO);

  List<ActivityDTO> findAll();

  void deleteById(Long id);

  void reservationListener(String message);
}

