package accountingsystem.main.repository;

import accountingsystem.main.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findByNameLike(String name);
}
