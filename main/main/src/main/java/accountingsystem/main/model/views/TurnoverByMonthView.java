package accountingsystem.main.model.views;

import accountingsystem.main.model.Product;
import accountingsystem.main.model.WorkService;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.jdbc.Work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Subselect("select * from public.turnover_by_month")
@Immutable
public class TurnoverByMonthView {

    @Id
    @Column(name="company_id")
    private Long companyId;

    private Long month;

    private Long year;

    private Long amount;
    @ManyToMany
    private List<Product> productList;
    @ManyToMany
    private List<WorkService> workServicesList;

    public TurnoverByMonthView() {
    }

    public TurnoverByMonthView(List<Product> productList, List<WorkService> workServicesList, Long month, Long year, Long amount) {
        this.month = month;
        this.year = year;
        this.amount = amount;
        this.productList=productList;
        this.workServicesList=workServicesList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<WorkService> getWorkServicesList() {
        return workServicesList;
    }

    public void setWorkServicesList(List<WorkService> workServicesList) {
        this.workServicesList = workServicesList;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getMonth() {
        return month;
    }

    public void setMonth(Long month) {
        this.month = month;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
