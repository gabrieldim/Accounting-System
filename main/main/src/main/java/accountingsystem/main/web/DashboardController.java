package accountingsystem.main.web;

import accountingsystem.main.model.Company;
import accountingsystem.main.model.User;
import accountingsystem.main.repository.UserRepository;
import accountingsystem.main.service.CompanyService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
@RequestMapping(path = "/dashboard")
public class DashboardController {
    private final UserRepository userRepository;
    private final CompanyService companyService;
    private final OAuth2AuthorizedClientService authorizedClientService;

    public DashboardController(UserRepository userRepository, CompanyService companyService, OAuth2AuthorizedClientService authorizedClientService) {
        this.userRepository = userRepository;
        this.companyService = companyService;
        this.authorizedClientService = authorizedClientService;
    }

    @GetMapping
    public String getDashboard(Model model, Principal principal) {
        String username = principal.getName();
        Optional<User> user = this.userRepository.findByUsername(username);

        if(!user.isPresent()) {
            String name = (String) ((DefaultOidcUser) ((OAuth2AuthenticationToken) principal).getPrincipal()).getAttributes().get("given_name");
            String lastName = (String) ((DefaultOidcUser) ((OAuth2AuthenticationToken) principal).getPrincipal()).getAttributes().get("family_name");
            String email = (String) ((DefaultOidcUser) ((OAuth2AuthenticationToken) principal).getPrincipal()).getAttributes().get("email");

            user =  Optional.of(this.userRepository.save(new User(username, "OAuth", name, lastName)));
            return "redirect:/dashboard";
        }

        Optional<Company> company = user.get().getCompanies().stream().findFirst();
        if(!company.isPresent()){
            return "redirect:/dashboard/addCompany";
        }

        model.addAttribute("earnings", 100000);
        return "index";
    }
    @GetMapping("/addCompany")
    public String getAddCompanyPage(){
        return "add-company";
    }

    @PostMapping("/addCompany")
    public String addCompany(@RequestParam String name,
                             @RequestParam String founder,
                             @RequestParam String address,
                             @RequestParam String incorporationDate,
                             @RequestParam String taxNumber,
                             @RequestParam String registeredNumber,
                             Principal principal) {

        String username = principal.getName();
        User user = this.userRepository.findByUsername(username).get();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        incorporationDate = incorporationDate + " 00:00";
        LocalDateTime dateTime = LocalDateTime.parse(incorporationDate, formatter);

        this.companyService.save(name, founder, address, dateTime, taxNumber, registeredNumber, user);

        return "redirect:/dashboard";
    }
}
