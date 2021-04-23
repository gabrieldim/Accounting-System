package accountingsystem.main.repository;


import accountingsystem.main.model.WorkService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WorkServiceRepository extends JpaRepository<WorkService, Long> {
    public List<WorkService> findByNameLike(String name);
}
