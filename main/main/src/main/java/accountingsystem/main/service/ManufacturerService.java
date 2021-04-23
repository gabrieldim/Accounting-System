package accountingsystem.main.service;

import accountingsystem.main.model.Company;
import accountingsystem.main.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {
    List<Manufacturer> findAll();
    Manufacturer findById(Long id);
    List<Manufacturer> findByNameLike(String name);
    Manufacturer save( String name,  String country);
    Manufacturer deleteById(Long Id);
}
