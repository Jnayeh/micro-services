package mourad.micro.services.impl;

import lombok.AllArgsConstructor;
import mourad.micro.models.Activity;
import mourad.micro.models.dto.ActivityDto;
import mourad.micro.repositories.ActivityRepository;
import mourad.micro.services.ActivityService;
import mourad.micro.utils.Utils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ActivityServiceImpl implements ActivityService {

  ActivityRepository activityRepository;

  @Override
  public List<ActivityDto> getAll(Integer pageNumber, String property, Sort.Direction direction) {
    if (pageNumber == null)
      return activityRepository.findAll().stream().map(Utils::mapToActivityDto).toList();
    return activityRepository.findAll(PageRequest.of((pageNumber <= 0 ? 1 : pageNumber) - 1, 10, Sort.by(List.of(Sort.Order.by(StringUtils.hasText(property) ? property : "id").with(direction)))))
        .stream().map(Utils::mapToActivityDto).toList();
  }

  @Override
  public ActivityDto getById(Integer id) {
    return Utils.mapToActivityDto((activityRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND))));
  }

  @Override
  public ActivityDto add(Activity activity) {
    return Utils.mapToActivityDto(activityRepository.save(activity));
  }


  @Override
  public ActivityDto update(Activity activity) {
    activityRepository.findById(activity.getId())
        .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    return Utils.mapToActivityDto(activityRepository.save(activity));
  }


  @Override
  public void delete(Integer id) {
    activityRepository.findById(id)
        .ifPresent(act -> activityRepository.delete(act));
    throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
  }


  public List<ActivityDto> getActiveActivities(Integer pageNumber, String property, Sort.Direction direction) {
    if (pageNumber == null) {
      return activityRepository.findByActiveTrue().stream().map(Utils::mapToActivityDto).toList();
    } else {
      Sort.Order order = Optional.ofNullable(property).filter(StringUtils::hasText)
          .map(prop-> Sort.Order.by(property).with(direction).ignoreCase())
              .orElse(Sort.Order.by("id").with(direction));

      PageRequest pageRequest = PageRequest.of((pageNumber <= 0 ? 1 : pageNumber) - 1, 10, Sort.by(order));

      return activityRepository.findByActiveTrue(pageRequest).stream().map(Utils::mapToActivityDto)
          .toList();
    }
  }

}
