package tn.esprit.spring.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/skier/all").permitAll() // Public access to this endpoint
                .anyRequest().authenticated() // All other endpoints require authentication
                .and()
                .csrf().disable(); // Disable CSRF for easier testing with Postman
    }
}
