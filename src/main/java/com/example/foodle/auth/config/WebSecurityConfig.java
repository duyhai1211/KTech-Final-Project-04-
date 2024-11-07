package com.example.foodle.auth.config;

import com.example.foodle.auth.jwt.JwtTokenFilter;
import com.example.foodle.auth.jwt.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtTokenUtils jwtTokenUtils;
    private final UserDetailsService manager;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    // Các endpoint công khai
                    auth.requestMatchers("/users/login", "/users/signin", "/users/signup", "/users/signup-owner", "/search", "/user/myinfo")
                            .permitAll();
                    auth.requestMatchers("/users", "/users/login", "/users/signup", "/users/orders")
                            .permitAll();
                    auth.requestMatchers("/static/**", "/favicon.ico", "/error").permitAll(); // Tất cả các tài nguyên tĩnh

                    // Quyền truy cập cho các endpoint cần xác thực
                    auth.requestMatchers("/users/update", "/users/profile", "/users/get-user-info",
                                    "/reservation/create", "/reservation/user/**", "/users/logout", "/users/review/**")
                            .authenticated();

                    // Chỉ cho phép ROLE_ADMIN truy cập các endpoint admin
                    auth.requestMatchers("/admin", "/admin/**").hasRole("ADMIN");

                    // Chỉ cho phép ROLE_OWNER vào các endpoint của chủ nhà hàng
                    auth.requestMatchers("/restaurant/**", "/reservation/restaurant/**").hasRole("OWNER");

                    // Tất cả các yêu cầu khác phải có ROLE_ACTIVE
                    auth.anyRequest().hasRole("ACTIVE");
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new JwtTokenFilter(jwtTokenUtils, manager), AuthorizationFilter.class);

        return http.build();
    }
}
