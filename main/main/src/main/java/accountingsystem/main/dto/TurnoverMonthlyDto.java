package accountingsystem.main.dto;

public class TurnoverMonthlyDto {

        private Long earnings;

        public TurnoverMonthlyDto() {
        }

        public TurnoverMonthlyDto(Long earnings) {
            this.earnings = earnings;
        }

        public Long getEarnings() {
            return earnings;
        }

        public void setEarnings(Long earnings) {
            this.earnings = earnings;
        }


}
