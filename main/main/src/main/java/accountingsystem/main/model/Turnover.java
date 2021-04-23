package accountingsystem.main.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Turnover {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private LocalDateTime date;

    @Column(name="number_products")
    private Integer numberProducts;
    @Column(name="amount")
    private Long amount;
    @ManyToMany
    private List<WorkService> workServiceList;
    @ManyToMany
    private List<Product> productList;
    @ManyToOne
    private Company company;

    public Turnover(LocalDateTime date, Integer numberProducts, Long amount, List<WorkService> workServiceList,
                    List<Product> productList, Company company) {

        this.date = date;
        this.numberProducts = numberProducts;
        this.amount = amount;
        this.workServiceList = workServiceList;
        this.productList = productList;
        this.company = company;
    }

    public List<WorkService> getWorkServiceList() {
        return workServiceList;
    }

    public void setWorkServiceList(List<WorkService> workServiceList) {
        this.workServiceList = workServiceList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Turnover() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }


    public Integer getNumberProducts() {
        return numberProducts;
    }

    public void setNumberProducts(Integer numberProducts) {
        this.numberProducts = numberProducts;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long sumProfit) {
        this.amount = sumProfit;
    }
}
