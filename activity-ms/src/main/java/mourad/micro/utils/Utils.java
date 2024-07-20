package mourad.micro.utils;

import mourad.micro.models.Activity;
import mourad.micro.models.dto.ActivityDto;

public class Utils {

  public static ActivityDto mapToActivityDto(Activity act) {
    return ActivityDto.builder()
        .id(act.getId())
        .active(act.isActive())
        .capacity(act.getCapacity())
        .createdAt(act.getCreatedAt())
        .description(act.getDescription())
        .price(act.getPrice())
        .discount(act.getDiscount())
        .duration(act.getDuration())
        .image(act.getImage())
        .build();
  }

  public static Activity mapToActivity(ActivityDto act) {
    return Activity.builder()
        .id(act.id())
        .active(act.active())
        .capacity(act.capacity())
        .description(act.description())
        .price(act.price())
        .discount(act.discount())
        .duration(act.duration())
        .image(act.image())
        .createdAt(act.createdAt())
        .build();
  }
}
