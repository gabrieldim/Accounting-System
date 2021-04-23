package accountingsystem.main.web;

import accountingsystem.main.model.User;
import accountingsystem.main.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@Controller
public class RegisterController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterController(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @GetMapping("/register")
    public String getRegisterPage(){
        return "register";
    }

    @PostMapping("/register")
    @ResponseBody
    public RedirectView registerUser(@RequestParam(name="firstName") String firstName,
                               @RequestParam(name="lastName") String lastName,
                               @RequestParam(name="email") String email,
                               @RequestParam(name="password") String password,
                               @RequestParam(name="repeatPassword") String repeatPassword
    ){

        if(firstName == null || firstName.equals("") || lastName == null || lastName.equals("") || email==null ||
        email.equals("") || password==null){
            return new RedirectView("/register");
            //define exception if needed
        }
        if(!password.equals(repeatPassword)){
            return new RedirectView("/register");
        }
        String hashedPassword=passwordEncoder.encode(password);
        this.userRepository.save(new User(email,hashedPassword,firstName,lastName));
        return new RedirectView("/dashboard");

    }


}
