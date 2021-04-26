package accountingsystem.main.dto;

public class RevenueSourceDto {
    private Long productsRevenue;

    private Long servicesRevenue;

    public RevenueSourceDto() {
    }

    public RevenueSourceDto(Long productsRevenue, Long servicesRevenue) {
        this.productsRevenue = productsRevenue;
        this.servicesRevenue = servicesRevenue;
    }

    public Long getProductsRevenue() {
        return productsRevenue;
    }

    public void setProductsRevenue(Long productsRevenue) {
        this.productsRevenue = productsRevenue;
    }

    public Long getServicesRevenue() {
        return servicesRevenue;
    }

    public void setServicesRevenue(Long servicesRevenue) {
        this.servicesRevenue = servicesRevenue;
    }
}
