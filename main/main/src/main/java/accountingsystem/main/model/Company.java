package accountingsystem.main.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    private String founder;
    @ManyToMany
    private List<Product> products;

    @ManyToMany
    private List<WorkService> workServices;

    private String address;

    @Column(name="incorporation_date")
    private LocalDateTime incorporationDate;

    @Column(name="tax_number")
    private String taxNumber;

    @Column(name="registered_number")
    private String registeredNumber;

    @ManyToOne
    private User user;

    private Long soldProducts;

    private Long soldServices;
    
    private Long revenueFromProducts;

    private Long revenueFromServices;

    public Long getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Long soldProducts) {
        this.soldProducts = soldProducts;
    }

    public Long getSoldServices() {
        return soldServices;
    }

    public void setSoldServices(Long soldServices) {
        this.soldServices = soldServices;
    }

    public Company(String name, String founder, String address, LocalDateTime incorporationDate,
                   String taxNumber, String registeredNumber, User user) {
        this.name = name;
        this.soldProducts = 0l;
        this.soldServices = 0l;
        this.revenueFromProducts = 0l;
        this.revenueFromServices = 0l;
        this.founder = founder;
        this.address = address;
        this.incorporationDate = incorporationDate;
        this.taxNumber = taxNumber;
        this.registeredNumber = registeredNumber;
        this.user = user;
    }

    public Company(String name, String founder, List<Product> products, List<WorkService> workServices,
                   String address,
                   LocalDateTime incorporationDate, String taxNumber, String registeredNumber, User user) {
        this.name = name;
        this.founder = founder;
        this.products = products;
        this.soldProducts = 0l;
        this.soldServices = 0l;
        this.revenueFromProducts = 0l;
        this.revenueFromServices = 0l;
        this.workServices = workServices;
        this.address = address;
        this.incorporationDate = incorporationDate;
        this.taxNumber = taxNumber;
        this.registeredNumber = registeredNumber;
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<WorkService> getWorkServices() {
        return workServices;
    }

    public void setWorkServices(List<WorkService> workServices) {
        this.workServices = workServices;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public Company() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getIncorporationDate() {
        return incorporationDate;
    }

    public void setIncorporationDate(LocalDateTime incorporationDate) {
        this.incorporationDate = incorporationDate;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getRegisteredNumber() {
        return registeredNumber;
    }

    public void setRegisteredNumber(String registeredNumber) {
        this.registeredNumber = registeredNumber;
    }

    public Long getRevenueFromProducts() {
        return revenueFromProducts;
    }

    public void setRevenueFromProducts(Long revenueFromProducts) {
        this.revenueFromProducts = revenueFromProducts;
    }

    public Long getRevenueFromServices() {
        return revenueFromServices;
    }

    public void setRevenueFromServices(Long revenueFromServices) {
        this.revenueFromServices = revenueFromServices;
    }
}
