package mourad.micro.services;

import mourad.micro.models.Activity;
import mourad.micro.models.dto.ActivityDto;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ActivityService {

  List<ActivityDto> getAll(Integer pageNumber, String property, Sort.Direction direction);


  ActivityDto getById(Integer id);

  ActivityDto add(Activity activity);

  ActivityDto update(Activity o);

  void delete(Integer id);

  public List<ActivityDto> getActiveActivities(Integer pageNumber, String property, Sort.Direction direction);

}