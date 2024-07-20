package mourad.micro.models;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Activity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String label;
  private String image;
  private String description;
  private int discount;
  private float price;
  private float duration;
  private int capacity;
  private boolean active;
  @OneToMany
  @JoinColumn(name = "user_id")
  private List<Reservation> reservations;

  @CreationTimestamp
  private Instant createdAt;
  @UpdateTimestamp
  private Instant modifiedAt;

}