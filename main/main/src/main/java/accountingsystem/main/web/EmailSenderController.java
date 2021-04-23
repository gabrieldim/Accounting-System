//package accountingsystem.main.web;
//
//import accountingsystem.main.service.TestSendingEmail;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/email")
//public class EmailSenderController {
//    private final TestSendingEmail testSendingEmail;
//
//    public EmailSenderController(TestSendingEmail testSendingEmail) {
//        this.testSendingEmail = testSendingEmail;
//    }
//
//
//    @GetMapping("/message")
//    public String sendMessage(){
//        testSendingEmail.sendSimpleMessage("accsystem2021@gmail.com","Test","Hey :)");
//    return "success";
//    }
//
//
//}
