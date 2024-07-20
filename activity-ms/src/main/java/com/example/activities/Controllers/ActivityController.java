package com.example.activities.Controllers;

import com.example.activities.DTO.ActivityDTO;
import com.example.activities.services.IActiviyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acitivities")
@Slf4j
@RequiredArgsConstructor
@RefreshScope
public class ActivityController {
  private final IActiviyService activiyService;

  @GetMapping
  public List<ActivityDTO> getAllActivities() {
    return activiyService.findAll();
  }

  @GetMapping("/{id}")
  public ActivityDTO getActivityById(@PathVariable Long id) {
    return activiyService.getById(id);
  }

  @PostMapping
  public ActivityDTO makeActivity(@RequestBody ActivityDTO activityDTO) {
    return activiyService.save(activityDTO);
  }

  @PutMapping("/{id}")
  public ActivityDTO modifyActivity(@RequestBody ActivityDTO activityDTO) {
    return activiyService.save(activityDTO);
  }

  @DeleteMapping("/{id}")
  public void deleteActivity(@PathVariable Long id) {
    activiyService.deleteById(id);
  }

}
