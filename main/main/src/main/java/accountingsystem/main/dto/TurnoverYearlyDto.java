package accountingsystem.main.dto;

public class TurnoverYearlyDto {

    private Long earnings;

    public TurnoverYearlyDto() {
    }

    public TurnoverYearlyDto(Long earnings) {
        this.earnings = earnings;
    }

    public Long getEarnings() {
        return earnings;
    }

    public void setEarnings(Long earnings) {
        this.earnings = earnings;
    }
}
