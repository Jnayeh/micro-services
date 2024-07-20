package com.example.activities.Mappers;

import com.example.activities.DTO.ActivityDTO;
import com.example.activities.Models.Activity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ActivityMapper {
  ActivityMapper INSTANCE = Mappers.getMapper(ActivityMapper.class);

  @Mapping(source = "id", target = "activityId")
  ActivityDTO toDto(Activity activity);

  @Mapping(source = "activityId", target = "id")
  Activity toEntity(ActivityDTO activityDTO);

}
