package accountingsystem.main.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    public static class UserLoginAuthentication extends WebSecurityConfigurerAdapter{}
    private final PasswordEncoder passwordEncoder;
    private final CustomUsernamePasswordAuthenticationProvider authenticationProvider;
    public WebSecurityConfig(PasswordEncoder passwordEncoder,CustomUsernamePasswordAuthenticationProvider authenticationProvider){
        this.passwordEncoder=passwordEncoder;
        this.authenticationProvider=authenticationProvider;

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/images/**", "/css/**","/js/**","/oauth2/**", "/vendor/**", "/img/**","/register").permitAll() // /images,/css,/js se defaultni lokacii za spring za cuvanje na staticni resursi: .css files, sliki i .js fajlovi. Site lokalni resursi stavajte gi vo tie folderi inaku Spring Security ke gi blokira.
                .antMatchers("/company","/manufacturer","/product","/workservice").hasRole("USER")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/login").permitAll()
                .failureUrl("/auth/login?error=BadCredentials")
                .defaultSuccessUrl("/dashboard",true)
                .and()
                .logout()
                .logoutUrl("/auth/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/auth/login")
        .and()
        .oauth2Login()
                .defaultSuccessUrl("/dashboard",true);



    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

}
