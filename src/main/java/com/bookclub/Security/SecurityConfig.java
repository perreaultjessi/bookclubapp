package com.bookclub.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        //create a local variable named encoder of type PasswordEncoder
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        //use the encoder to configure authentication
        auth
                .inMemoryAuthentication()
                .withUser("user").password(encoder.encode("password")).roles("USER")
                .and()
                .withUser("testuser01").password(encoder.encode("password02")).roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/monthly-books/list", "/monthly-books/new", "/monthly-books").hasRole("ADMIN") // Restrict access to ADMIN users
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll() //access allowance to login page without need for authentication
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .permitAll(); //access allowance to logout page without need for authentication
    }
}

