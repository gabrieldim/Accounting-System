package accountingsystem.main.web.api;

import accountingsystem.main.model.User;
import accountingsystem.main.resource.response.NameAndLastName;
import accountingsystem.main.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<NameAndLastName> getUsername(Principal principal){
        NameAndLastName nameAndLastName = new NameAndLastName();

        // OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) principal;
        // String name = token.getPrincipal().getAttribute("given_name");
        // String lastName = token.getPrincipal().getAttribute("family_name");

        String username = principal.getName();
        User user = this.userService.findByUsername(username);

        nameAndLastName.setFirstName(user.getFirstName());
        nameAndLastName.setLastName(user.getLastName());


     return ResponseEntity.ok(nameAndLastName);
    }

}
