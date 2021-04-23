package accountingsystem.main.web;

import accountingsystem.main.model.Company;
import accountingsystem.main.model.Turnover;
import accountingsystem.main.model.User;
import accountingsystem.main.repository.TurnoverRepository;
import accountingsystem.main.repository.UserRepository;
import accountingsystem.main.service.CompanyService;
import accountingsystem.main.service.TestPDFExporter;
import accountingsystem.main.service.TurnoverService;
import org.apache.tomcat.jni.Local;
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
import java.util.Date;
import java.util.List;

@Controller
public class TurnoverController {
    private final TurnoverService turnoverService;
    private final UserRepository userRepository;
    private final TurnoverRepository turnoverRepository;
    private final CompanyService companyService;

    public TurnoverController(CompanyService companyService,TurnoverService turnoverService, UserRepository userRepository, TurnoverRepository turnoverRepository) {
        this.turnoverService = turnoverService;
        this.userRepository = userRepository;
        this.turnoverRepository = turnoverRepository;
        this.companyService=companyService;
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
            @RequestParam String amount,
            @RequestParam String date,
            @RequestParam String products,
            Principal principal) {
        String username = principal.getName();
        User user = this.userRepository.findByUsername(username).get();
        Company company = user.getCompanies().stream().findFirst().get();

        Turnover turnover = new Turnover();

        turnover.setAmount(Long.parseLong(amount));
        turnover.setCompany(company);
        turnover.setDate(LocalDateTime.now());
        turnover.setNumberProducts(Integer.parseInt(products));
        this.turnoverRepository.save(turnover);

        return "redirect:/dashboard";
    }


}
