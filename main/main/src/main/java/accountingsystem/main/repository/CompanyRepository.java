package accountingsystem.main.repository;

import accountingsystem.main.model.Company;
import accountingsystem.main.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findAllByNameLike(String name);
    List<Company> findAllByUser(User user);
}
