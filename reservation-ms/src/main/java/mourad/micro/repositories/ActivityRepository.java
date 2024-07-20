package mourad.micro.repositories;
import mourad.micro.models.Activity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {
  List<Activity> findByActiveTrue(PageRequest pageRequest);
  List<Activity> findByActiveTrue();

  @Query("SELECT  DISTINCT a FROM Activity a WHERE a.active= true and a.isFavorite=true")
  List<Activity> findFavoritesActivities();

}


