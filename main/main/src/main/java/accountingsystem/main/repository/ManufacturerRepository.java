package accountingsystem.main.repository;

import accountingsystem.main.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
 public List<Manufacturer> findByNameLike(String name);

}
