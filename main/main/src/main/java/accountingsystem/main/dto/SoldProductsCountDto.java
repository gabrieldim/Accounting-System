package accountingsystem.main.dto;

public class SoldProductsCountDto {
    private Long soldProductsCounter;

    public SoldProductsCountDto(Long soldProductsCounter) {
        this.soldProductsCounter = soldProductsCounter;
    }
    public SoldProductsCountDto() {
    }

    public Long getSoldProductsCounter() {
        return soldProductsCounter;
    }

    public void setSoldProductsCounter(Long soldProductsCounter) {
        this.soldProductsCounter = soldProductsCounter;
    }
}
