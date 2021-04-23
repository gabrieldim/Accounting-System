package accountingsystem.main.resource.response;

import java.time.LocalDateTime;

public class MonthlyTurnover {
    private LocalDateTime localDateTime;
    private Double turnover;

    public MonthlyTurnover() {
    }

    public MonthlyTurnover(LocalDateTime localDateTime, Double turnover) {
        this.localDateTime = localDateTime;
        this.turnover = turnover;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Double getTurnover() {
        return turnover;
    }

    public void setTurnover(Double turnover) {
        this.turnover = turnover;
    }

}
