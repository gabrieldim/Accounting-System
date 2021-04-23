package accountingsystem.main.factory;

import accountingsystem.main.resource.response.CompanyTotalTurnover;

public class CompanyFactory {
    public static CompanyTotalTurnover createCompanyTotalTurnover(
            Long companyId,
            String name,
            Double turnover){
        CompanyTotalTurnover companyTotalTurnover = new CompanyTotalTurnover(companyId,name,turnover);
    return companyTotalTurnover;
    }
}
