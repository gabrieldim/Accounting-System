package accountingsystem.main.web;

import accountingsystem.main.repository.UserRepository;
import accountingsystem.main.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;
    private final UserRepository userRepository;

    public CompanyController(CompanyService companyService, UserRepository userRepository) {
        this.companyService = companyService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String listCompanies(Model model){
        model.addAttribute("companies",this.companyService.findAll());
        return "list-companies";
    }



}
