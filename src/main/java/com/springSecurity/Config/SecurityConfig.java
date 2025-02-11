package com.springSecurity.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JWTFilter jwtFilter;


//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetail normalUser = User.withUsername("swarnendu")
//                .password("hgjgjh").roles("admin").build();
//        return
//    }





    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable();
        //haap
         http.authorizeHttpRequests().anyRequest().permitAll();
      //  http.addFilterBefore(jwtFilter, AuthorizationFilter.class);
//        http.authorizeHttpRequests()
//                .requestMatchers("/api/v1/auth/signup",
//                        "/api/v1/auth/usersign",
//                        "/api/v1/auth/content-manager-signup",
//                        "/api/v1/auth/blog-manager-signup",
//                        "/api/v1/auth/login-otp")
//                .permitAll()
//                .requestMatchers("/api/v1/cars/add-car").hasRole("CONTENTMANAGER").
//                anyRequest().authenticated();

        return http.build();
    }


    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
