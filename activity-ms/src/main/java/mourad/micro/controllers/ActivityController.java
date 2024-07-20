package mourad.micro.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import mourad.micro.models.Activity;
import mourad.micro.models.dto.ActivityDto;
import mourad.micro.services.ActivityService;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Activity Management")
@RestController
@AllArgsConstructor
@RequestMapping("/activities")
public class ActivityController {
  ActivityService activityService;

  @PostMapping()
  public ActivityDto addActivity(@RequestBody Activity activity) {
    return activityService.add(activity);
  }

  @PutMapping("/{id}")
  public ActivityDto update(@RequestBody Activity activity, @PathVariable("id") Integer id) {
    activity.setId(id);
    return activityService.update(activity);
  }

  @GetMapping("/{id}")
  public ActivityDto getOne(@PathVariable("id") Integer id) {
    return activityService.getById(id);
  }

  @GetMapping
  public List<ActivityDto> getAll(@RequestParam(value = "page", required = false) Integer page,
                                  @RequestParam(value = "sort", required = false) String sort,
                                  @RequestParam(value = "dir", required = false) String dir) {
    Sort.Direction sortDir = Sort.Direction.fromString(Optional.ofNullable(dir)
        .filter(StringUtils::hasText)
        .map(String::toUpperCase)
        .orElse(Sort.Direction.ASC.name()));
    return activityService.getAll(page, sort, sortDir);
  }

  @GetMapping("/active")
  public List<ActivityDto> getActiveActivities(@RequestParam(value = "page", required = false) Integer page,
                                               @RequestParam(value = "sort", required = false) String sort,
                                               @RequestParam(value = "dir", required = false) String dir) {
    Sort.Direction sortDir = Sort.Direction.fromString(Optional.ofNullable(dir)
        .filter(StringUtils::hasText)
        .map(String::toUpperCase)
        .orElse(Sort.Direction.ASC.name()));
    return activityService.getActiveActivities(page, sort, sortDir);
  }

  @DeleteMapping("{activityId}")
  public void delete(@PathVariable Integer activityId) {
    activityService.delete(activityId);
  }
}