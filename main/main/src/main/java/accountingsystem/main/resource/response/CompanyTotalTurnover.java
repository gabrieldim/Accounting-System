package accountingsystem.main.resource.response;

public class CompanyTotalTurnover {
    private Long Id;
    private String companyName;
    private Double turnover;

    public CompanyTotalTurnover() {
    }

    public CompanyTotalTurnover(Long id, String companyName, Double turnover) {
        Id = id;
        this.companyName = companyName;
        this.turnover = turnover;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Double getTurnover() {
        return turnover;
    }

    public void setTurnover(Double turnover) {
        this.turnover = turnover;
    }


}
