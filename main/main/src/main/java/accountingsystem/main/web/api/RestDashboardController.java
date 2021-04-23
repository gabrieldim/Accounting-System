package accountingsystem.main.web.api;

import accountingsystem.main.dto.TurnoverMonthlyDto;
import accountingsystem.main.dto.TurnoverYearlyDto;
import accountingsystem.main.model.Company;
import accountingsystem.main.model.User;
import accountingsystem.main.repository.TurnoverRepository;
import accountingsystem.main.repository.UserRepository;
import accountingsystem.main.repository.views.TurnoverByMonthInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/dashboard")
public class RestDashboardController {

    private final TurnoverRepository turnoverRepository;
    private final UserRepository userRepository;

    public RestDashboardController(
            TurnoverRepository turnoverRepository,
            UserRepository userRepository) {
        this.turnoverRepository = turnoverRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/earnings")
    public ResponseEntity<TurnoverMonthlyDto> getEarnings(Principal principal) {
        TurnoverMonthlyDto turnoverMonthly = new TurnoverMonthlyDto();

        LocalDateTime now = LocalDateTime.now();


        String username = principal.getName();
        User user = this.userRepository.findByUsername(username).get();

        Company company = user.getCompanies().stream().findFirst().get();
        Long companyId = company.getId();

        List<TurnoverByMonthInterface> turnoverByMonthInterfaceList =
                this.turnoverRepository.getTurnoverMonthly().stream()
                        .filter(x -> x.getcompany_id().equals(companyId))
                        .collect(Collectors.toList());

       Optional<TurnoverByMonthInterface> currentMonth = turnoverByMonthInterfaceList
                .stream()
                .filter(x->(x.getyear()== (now.getYear())) &&(x.getmonth()==Long.parseLong(String.valueOf(now.getMonth().ordinal()+1))))
                .findFirst();



        turnoverMonthly.setEarnings(currentMonth.get().getamount());
        return ResponseEntity.ok(turnoverMonthly);
    }

    @GetMapping("/earningsYearly")
    public ResponseEntity<TurnoverYearlyDto> getEarningYearly(Principal principal) {
        TurnoverYearlyDto turnoverYearlyDto = new TurnoverYearlyDto();

        LocalDateTime now = LocalDateTime.now();


        String username = principal.getName();
        User user = this.userRepository.findByUsername(username).get();

        Company company = user.getCompanies().stream().findFirst().get();
        Long companyId = company.getId();

        List<TurnoverByMonthInterface> turnoverByMonthInterfaceList =
                this.turnoverRepository.getTurnoverMonthly().stream()
                        .filter(x -> x.getcompany_id().equals(companyId))
                        .collect(Collectors.toList());

        Long currentYear = turnoverByMonthInterfaceList
                .stream()
                .filter(x->(x.getyear()== (now.getYear())))
                .mapToLong(x -> x.getamount()).sum();

        turnoverYearlyDto.setEarnings(currentYear);
        return ResponseEntity.ok(turnoverYearlyDto);
    }


}
