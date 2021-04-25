package accountingsystem.main.service;

import accountingsystem.main.model.Manufacturer;
import accountingsystem.main.model.Product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
    List<Product> findByNameLike(String name);
    Product save(
            String description,
            String name,
            Manufacturer manufacturer,
            Long price,
            LocalDateTime date,
            LocalDateTime expirationDate);
    Product deleteById(Long Id);
    Product save(Product product);
}
