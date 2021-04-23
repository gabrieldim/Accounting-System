package accountingsystem.main.service.impl;

import accountingsystem.main.exceptions.TurnoverNotFoundException;
import accountingsystem.main.model.Company;
import accountingsystem.main.model.Product;
import accountingsystem.main.model.Turnover;
import accountingsystem.main.model.WorkService;
import accountingsystem.main.repository.TurnoverRepository;
import accountingsystem.main.service.CompanyService;
import accountingsystem.main.service.TurnoverService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TurnoverServiceImpl implements TurnoverService {
    private final TurnoverRepository turnoverRepository;
    private final CompanyService companyService;
    public TurnoverServiceImpl(TurnoverRepository turnoverRepository, CompanyService companyService) {
        this.turnoverRepository = turnoverRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Turnover> findAll() {
        return turnoverRepository.findAll();
    }

    @Override
    public Turnover findById(Long id) {

        return turnoverRepository.findById(id).orElseThrow(TurnoverNotFoundException::new);
    }


    @Override
    public Turnover save(
            LocalDateTime date,
            Integer numberProducts,
            Long sumProfit,
            List<WorkService> workServiceList,
            List<Product> productList,
            Company company) {
        Turnover turnover = new Turnover(
                date,
                numberProducts,
                sumProfit,
                workServiceList,
                productList,
                company);
        turnoverRepository.save(turnover);
        return turnover;
    }

    @Override
    public Turnover deleteById(Long Id) {
        Turnover turnover = turnoverRepository.findById(Id).orElseThrow(TurnoverNotFoundException::new);
        turnoverRepository.deleteById(Id);
        return turnover;
    }

    @Override
    public Double getTotalTurnover(Long companyId) {
        Company company = this.companyService.findById(companyId).orElseThrow(RuntimeException::new);
        return this.turnoverRepository.calculateTotalTurnover(company);
    }
    @Override
    public List<Turnover> getAllByCompanyAndMonth(Long companyId,LocalDateTime date){
        Company company=this.companyService.findById(companyId).orElseThrow(RuntimeException::new);
        return this.turnoverRepository.getAllByCompanyAndDateIsContaining(company,date);
    }


}
