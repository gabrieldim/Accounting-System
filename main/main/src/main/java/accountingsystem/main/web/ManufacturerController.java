package accountingsystem.main.web;

import accountingsystem.main.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manufacturer")
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }
    @GetMapping
    public String listManufacturers(Model model){
    model.addAttribute("manufacturers",this.manufacturerService.findAll());
    return "list-manufacturers";
    }


}
