package accountingsystem.main.dto;

public class SoldWorkServicesCountDto {
    private Long soldWorkServicesCount;

    public Long getSoldWorkServicesCount() {
        return soldWorkServicesCount;
    }

    public void setSoldWorkServicesCount(Long soldWorkServicesCount) {
        this.soldWorkServicesCount = soldWorkServicesCount;
    }

    public SoldWorkServicesCountDto(Long soldWorkServicesCount) {
        this.soldWorkServicesCount = soldWorkServicesCount;
    }
}
