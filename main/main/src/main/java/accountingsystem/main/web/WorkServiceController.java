package accountingsystem.main.web;

import accountingsystem.main.model.Company;
import accountingsystem.main.model.User;
import accountingsystem.main.repository.CompanyRepository;
import accountingsystem.main.repository.UserRepository;
import accountingsystem.main.service.WorkServicesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/workservice")
public class WorkServiceController {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final WorkServicesService workServicesService;

    public WorkServiceController(
            UserRepository userRepository,
            CompanyRepository companyRepository,
            WorkServicesService workServicesService) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.workServicesService = workServicesService;
    }

    @GetMapping
    public String listAllServices(Model model)
    {
        model.addAttribute("services",workServicesService.findAll());
        return "list-services";
    }

    @GetMapping("/insert")
    public String getInsertWorkServicePage() {
        return "insert-services";
    }

    @PostMapping("/insert")
    public String insertWorkService(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String price,
            Principal principal) {

        String username = principal.getName();
        User user = this.userRepository.findByUsername(username).get();
        Company company = user.getCompanies().stream().findFirst().get();


        company.getWorkServices().add(this.workServicesService.save(name, description, Long.parseLong(price)));
        this.companyRepository.save(company);

        return "redirect:/dashboard";
    }


}
