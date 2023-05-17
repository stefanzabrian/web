package com.obsm.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests()
                // restricted access
                .requestMatchers("/addProduct").hasAuthority("ADMIN")
                .requestMatchers("/register-admin").hasAuthority("ADMIN")
                .requestMatchers("/update-admin-profile").hasAnyAuthority("ADMIN", "MODERATOR")
                // public access
                .requestMatchers(
                        "/javax/**",
                        "/css/**",
                        "/img/**",
                        "/js/**",
                        "/vendor/**",
                        "/webjars/**",
                        "/forgot-password",
                        "/register"
                ).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout").permitAll()
                .and()
                .build();

        //  .antMatchers("/user/**").hasRole("USER")
        //  .antMatchers("/admin/**", "/product/new", "/product/delete").hasRole("ADMIN")
    }
}
