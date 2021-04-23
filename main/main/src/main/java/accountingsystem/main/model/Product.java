package accountingsystem.main.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String description;
    private String name;
    private Long price;
    private Long procurementPrice;
    private Long profit;
    private LocalDateTime date;
    @Column(name="expiration_date")
    private LocalDateTime expirationDate;
    @ManyToOne
    private Manufacturer manufacturer;

    public Long getProcurementPrice() {
        return procurementPrice;
    }

    public void setProcurementPrice(Long procurementPrice) {
        this.procurementPrice = procurementPrice;
    }

    public Long getProfit() {
        return profit;
    }

    public void setProfit(Long profit) {
        this.profit = profit;
    }

    public Product() {
    }

    public Product(String description, String name, Manufacturer manufacturer, Long price, LocalDateTime date,
                   LocalDateTime expirationDate) {

        this.description = description;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.date = date;
        this.expirationDate = expirationDate;
    }
    public void setId(Long id) {
        Id = id;
    }
    public Long getId() {
        return Id;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
}
