package accountingsystem.main.service;

import accountingsystem.main.model.Company;
import accountingsystem.main.model.WorkService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface WorkServicesService {
    List<WorkService> findAll();
    WorkService findById(Long id);
    List<WorkService> findByNameLike(String name);
    WorkService save(String name,String description,Long price);
    WorkService deleteById(Long Id);
}
