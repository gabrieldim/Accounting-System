package accountingsystem.main.service.impl;

import accountingsystem.main.exceptions.ManufacturerNotFoundException;
import accountingsystem.main.model.Manufacturer;
import accountingsystem.main.repository.ManufacturerRepository;
import accountingsystem.main.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Manufacturer> findAll() {
        return this.manufacturerRepository.findAll();
    }

    @Override
    public Manufacturer findById(Long id) {
        return this.manufacturerRepository.findById(id).orElseThrow(ManufacturerNotFoundException::new);
    }

    @Override
    public List<Manufacturer> findByNameLike(String name) {
        return this.manufacturerRepository.findByNameLike(name);
    }

    @Override
    public Manufacturer save( String name,  String country) {
      Manufacturer manufacturer = new Manufacturer(name,country);
      this.manufacturerRepository.save(manufacturer);
      return manufacturer;
    }

    @Override
    public Manufacturer deleteById(Long Id) {
        Manufacturer manufacturer = this.manufacturerRepository
                .findById(Id)
                .orElseThrow(ManufacturerNotFoundException::new);

         this.manufacturerRepository.deleteById(Id);
         return manufacturer;
    }
}
