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
                .requestMatchers("/register-admin").hasAuthority("ADMIN")
                .requestMatchers("/update-admin-profile").hasAnyAuthority("ADMIN","MODERATOR")
                .requestMatchers("/updateProduct/**").hasAuthority("ADMIN")
                .requestMatchers("/addProduct").hasAuthority("ADMIN")
                .requestMatchers("/deleteProduct/**").hasAuthority("ADMIN")
                .requestMatchers("/portal").hasAnyAuthority("ADMIN","MODERATOR")
                .requestMatchers("/addProject").hasAuthority("ADMIN")
                .requestMatchers("/addTask").hasAuthority("ADMIN")
                .requestMatchers("/view-all-orders").hasAuthority("ADMIN")
                .requestMatchers("/admin-view-order/**").hasAuthority("ADMIN")
                .requestMatchers("/update-order/**").hasAuthority("ADMIN")
                .requestMatchers("/admin-delete-order/**").hasAuthority("ADMIN")
                // public access
                .requestMatchers(
                        "/shopping-cart-add/**",
                        "/shopping-cart-remove/**",
                        "/shopping-cart/**",
                        "/shopping-cart-add-item/**",
                        "/shopping-cart-add-overview/**",
                        "/javax/**",
                        "/css/**",
                        "/img/**",
                        "/js/**",
                        "*/js/**",
                        "/vendor/**",
                        "/webjars/**",
                        "/forgot-password",
                        "/register",
                        "/about",
                        "/blog-home",
                        "/blog-post",
                        "/contact",
                        "/faq",
                        "/index",
                        "/",
                        "/home",
                        "/portfolio-item/**",
                        "/portfolio-overview",
                        "/pricing"
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
