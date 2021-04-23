package accountingsystem.main.repository;

import accountingsystem.main.model.Company;
import accountingsystem.main.model.Turnover;
import accountingsystem.main.repository.views.TurnoverByMonthInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface TurnoverRepository extends JpaRepository<Turnover, Long> {

    @Query(value = "select t from Turnover as t where t.company=?1")
    List<Turnover> filterByCompany(Long id);

    @Query("select sum(t.amount) as totalTurnover from Turnover as t where t.company=?1 group by t.Id")
    Double calculateTotalTurnover(Company company);

    @Query(nativeQuery = true,value = "select * from public.turnover_by_month")
    List<TurnoverByMonthInterface> getTurnoverMonthly();

   List<Turnover> getAllByCompanyAndDateIsContaining(Company company, LocalDateTime date);


}
