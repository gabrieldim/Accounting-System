package accountingsystem.main.service;


import accountingsystem.main.model.Role;
import accountingsystem.main.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);

}
