package accountingsystem.main.web;

import accountingsystem.main.model.*;
import accountingsystem.main.repository.TurnoverRepository;
import accountingsystem.main.repository.UserRepository;
import accountingsystem.main.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
public class TurnoverController {
    private final TurnoverService turnoverService;
    private final UserRepository userRepository;
    private final TurnoverRepository turnoverRepository;
    private final CompanyService companyService;
    private final ProductService productService;
    private final WorkServicesService workServicesService;

    public TurnoverController(TurnoverService turnoverService,
                              UserRepository userRepository,
                              TurnoverRepository turnoverRepository,
                              CompanyService companyService,
                              ProductService productService,
                              WorkServicesService workServicesService) {
        this.turnoverService = turnoverService;
        this.userRepository = userRepository;
        this.turnoverRepository = turnoverRepository;
        this.companyService = companyService;
        this.productService = productService;
        this.workServicesService = workServicesService;
    }


    @GetMapping
    public String listAllTurnovers(Model model){
        model.addAttribute("turnovers",turnoverService.findAll());
        return "list-turnovers";
    }

    @GetMapping("/turnover/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=turnover_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        TestPDFExporter exporter = new TestPDFExporter();
        exporter.export(response);
    }

    @GetMapping("/turnover/export/pdf/{companyID}/{date}")
    public void exportToPDFwithDate(HttpServletResponse response, @PathVariable Long companyID,@PathVariable LocalDateTime date) throws IOException{
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=turnover_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        List<Turnover> turnoverList=this.turnoverService.getAllByCompanyAndMonth(companyID,date);

        TestPDFExporter exporter = new TestPDFExporter();
        exporter.export(response);
    }

    @GetMapping("/turnover/insert")
    public String insertTurnover() {
        return "insert-turnover";
    }

    @PostMapping("/turnover/insert")
    public String insertDailyTurnover(
            @RequestParam String productId,
            @RequestParam String amount,
            @RequestParam String date,
            Principal principal) {
        String username = principal.getName();
        User user = this.userRepository.findByUsername(username).get();
        Company company = user.getCompanies().stream().findFirst().get();

        Turnover turnover = new Turnover();

        turnover.setAmount(Long.parseLong(amount));
        turnover.setCompany(company);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String dateSold = date + " 00:00";
        LocalDateTime dateTime = LocalDateTime.parse(dateSold, formatter);

        turnover.setDate(dateTime);

        company.setSoldProducts(company.getSoldProducts() + Long.valueOf(amount));

        Product product = productService.findById(Long.valueOf(productId));

        Long totalAmount = product.getPrice() * Long.valueOf(amount);
        turnover.setAmount(totalAmount);
        company.setRevenueFromProducts(company.getRevenueFromProducts() + totalAmount);
        this.companyService.save(company);
        this.turnoverRepository.save(turnover);

        return "redirect:/dashboard";
    }
    @PostMapping("/turnover/insert/services")
    public String insertDailyTurnoverServices(
            @RequestParam String serviceId,
            @RequestParam String amount,
            @RequestParam String date,
            Principal principal) {
        String username = principal.getName();
        User user = this.userRepository.findByUsername(username).get();
        Company company = user.getCompanies().stream().findFirst().get();

        Turnover turnover = new Turnover();

        turnover.setAmount(Long.parseLong(amount));
        turnover.setCompany(company);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String dateSold = date + " 00:00";
        LocalDateTime dateTime = LocalDateTime.parse(dateSold, formatter);

        turnover.setDate(dateTime);

        company.setSoldServices(company.getSoldServices() + Long.valueOf(amount));

        WorkService workService = workServicesService.findById(Long.valueOf(serviceId));

        Long totalAmount = workService.getPrice() * Long.valueOf(amount);
        turnover.setAmount(totalAmount);

        company.setRevenueFromServices(company.getRevenueFromServices() + totalAmount);
        this.companyService.save(company);
        this.turnoverRepository.save(turnover);

        return "redirect:/dashboard";
    }

    @GetMapping("/turnover/insert/services")
    public String getInsertTurnoverServicesPage(){
        return "insert-turnover-services";
    }

}
