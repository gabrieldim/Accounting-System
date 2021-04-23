package accountingsystem.main.repository.views;

import accountingsystem.main.model.views.TurnoverByMonthView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurnoverByMonthViewRepository extends JpaRepository<TurnoverByMonthView,Long> {

}