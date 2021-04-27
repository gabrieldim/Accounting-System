package accountingsystem.main.web;

import accountingsystem.main.model.Company;
import accountingsystem.main.model.User;
import accountingsystem.main.repository.UserRepository;
import accountingsystem.main.service.EmailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/email")
public class EmailSenderController {

    private final UserRepository userRepository;
    private final EmailSender emailSender;

    public EmailSenderController(UserRepository userRepository, EmailSender emailSender) {
        this.userRepository = userRepository;
        this.emailSender = emailSender;
    }


    @GetMapping("/message")
    public String sendMessage(){
        emailSender.sendSimpleMessage("accsystem2021@gmail.com","Test","Hey :)");
        return "success";
    }

    @GetMapping("/report")
    public String getSendReportPage() {
        return "send-email";
    }

    @PostMapping("/report")
    public String sendReport(@RequestParam String email, Principal principal) {

        String username = principal.getName();
        User user = userRepository.findByUsername(username).get();

        Company company = user.getCompanies().stream().findFirst().get();

        Long totalProductsSold = company.getSoldProducts();
        Long totalServicesSold = company.getSoldServices();

        String message = String.format("Total products sold: %d\n Total services sold: %d", totalProductsSold, totalServicesSold);

        emailSender.sendSimpleMessage(email,"Accounting System - Monthly Report",message);
        
        return "redirect:/dashboard";
    }


}
