package accountingsystem.main.resource.request;

import java.util.List;

public class AddCompanyRequest {
    private String name;
    private String address;
    private String founder;
    private String incorporationDate;
    private String taxNumber;
    private String registeredNumber;
    private Long userId;
    private List<Long> workServiceIds;
    private List<Long> productsIds;

    public AddCompanyRequest() {
    }

    public AddCompanyRequest(
            String name,
            String address,
            String founder,
            String incorporationDate,
            String taxNumber,
            String registeredNumber,
            Long userId,
            List<Long> workServiceIds,
            List<Long> productsIds) {
        this.name = name;
        this.address = address;
        this.founder = founder;
        this.incorporationDate = incorporationDate;
        this.taxNumber = taxNumber;
        this.registeredNumber = registeredNumber;
        this.userId = userId;
        this.workServiceIds = workServiceIds;
        this.productsIds = productsIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public String getIncorporationDate() {
        return incorporationDate;
    }

    public void setIncorporationDate(String incorporationDate) {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getWorkServiceIds() {
        return workServiceIds;
    }

    public void setWorkServiceIds(List<Long> workServiceIds) {
        this.workServiceIds = workServiceIds;
    }

    public List<Long> getProductsIds() {
        return productsIds;
    }

    public void setProductsIds(List<Long> productsIds) {
        this.productsIds = productsIds;
    }
}
