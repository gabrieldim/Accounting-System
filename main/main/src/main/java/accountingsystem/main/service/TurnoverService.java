package accountingsystem.main.service;

import accountingsystem.main.model.Company;
import accountingsystem.main.model.Product;
import accountingsystem.main.model.Turnover;
import accountingsystem.main.model.WorkService;

import java.time.LocalDateTime;
import java.util.List;

public interface TurnoverService {
    List<Turnover> findAll();
    Turnover findById(Long id);

    Turnover save(
            LocalDateTime date,
            Integer numberProducts,
            Long sumProfit,
            List<WorkService> workServiceList,
            List<Product> productList,
            Company company);
    Turnover deleteById(Long Id);

    Double getTotalTurnover(Long companyId);
    List<Turnover> getAllByCompanyAndMonth(Long companyId,LocalDateTime date);




}
