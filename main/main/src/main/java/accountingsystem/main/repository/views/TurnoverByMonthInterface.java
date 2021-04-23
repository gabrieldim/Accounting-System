package accountingsystem.main.repository.views;

import accountingsystem.main.model.Product;
import accountingsystem.main.model.WorkService;

import java.util.List;

public interface TurnoverByMonthInterface {

    Long getcompany_id();
    Long getmonth();
    Long getyear();
    Long getamount();
    List<Product> getProducts();
    List<WorkService> getWorkServices();
}
