package accountingsystem.main.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    @GetMapping(value = "/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping(value = "/logout")
    public String getLogout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/auth/login";
    }


}
