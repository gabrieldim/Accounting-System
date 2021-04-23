package accountingsystem.main.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/dashboard")
public class DashboardController {
    @GetMapping
    public String getDashboard(Model model) {
        model.addAttribute("earnings", 100000);
        return "index";
    }
}
