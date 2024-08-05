package com.agendapro.eval.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf
            .ignoringRequestMatchers("/h2-console/**", "/product/**")
        )
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/product/**").permitAll()
            .requestMatchers("/h2-console/**").permitAll()
            .anyRequest().authenticated()
        )
        .headers(headers -> headers
            .frameOptions(FrameOptionsConfig::sameOrigin
            )
        )
        .httpBasic(withDefaults());
    return http.build();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    UserDetails user = User.builder()
        .username("user")
        .password(passwordEncoder().encode("password"))
        .roles("USER")
        .build();
    return new InMemoryUserDetailsManager(user);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
