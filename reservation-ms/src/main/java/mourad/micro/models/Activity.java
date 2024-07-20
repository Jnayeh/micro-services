package mourad.micro.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Activity implements Serializable {
  @Id
  private Integer id;
  private String label;
  private String image;
  private String description;
  private int discount;
  private float price;
  private float duration;
  private int capacity;
  private boolean active;
}