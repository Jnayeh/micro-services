package mourad.micro.models.dto;

import lombok.Builder;

import java.time.Instant;

@Builder
public record ActivityDto(
    Integer id,
    String label,
    String image,
    String description,
    int discount,
    float price,
    float duration,
    int capacity,
    boolean active,
    Instant createdAt
) {}