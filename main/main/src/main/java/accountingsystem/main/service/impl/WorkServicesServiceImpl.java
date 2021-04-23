package accountingsystem.main.service.impl;

import accountingsystem.main.exceptions.ServiceNotFoundException;
import accountingsystem.main.model.WorkService;
import accountingsystem.main.repository.WorkServiceRepository;
import accountingsystem.main.service.WorkServicesService;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class WorkServicesServiceImpl implements WorkServicesService {

    private final WorkServiceRepository workServiceRepository;

    public WorkServicesServiceImpl(WorkServiceRepository workServiceRepository) {
        this.workServiceRepository = workServiceRepository;
    }

    @Override
    public List<WorkService> findAll() {
        return workServiceRepository.findAll();
    }

    @Override
    public WorkService findById(Long id) {
    return workServiceRepository.findById(id).orElseThrow(ServiceNotFoundException::new);
    }

    @Override
    public List<WorkService> findByNameLike(String name) {
        return workServiceRepository.findByNameLike(name);
    }

    @Override
    public WorkService save(String name,String description,Long price) {
        WorkService workService = new WorkService();
        workService.setName(name);
        workService.setDescription(description);
        workService.setPrice(price);
        workServiceRepository.save(workService);
        return workService;
    }

    @Override
    public WorkService deleteById(Long Id) {
        WorkService workService = workServiceRepository.findById(Id).orElseThrow(ServiceNotFoundException::new);
        workServiceRepository.deleteById(Id);
        return workService;
    }
}
