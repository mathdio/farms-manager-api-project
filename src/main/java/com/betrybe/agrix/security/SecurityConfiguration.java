package com.betrybe.agrix.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * The type Security configuration.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

//  private final SecurityFilter securityFilter;
//
//  @Autowired
//  public SecurityConfiguration(SecurityFilter securityFilter) {
//    this.securityFilter = securityFilter;
//  }

  /**
   * Security filter chain security filter chain.
   *
   * @param httpSecurity the http security
   * @return the security filter chain
   * @throws Exception the exception
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(
            request -> request
                .requestMatchers(HttpMethod.POST, "/persons").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .anyRequest().authenticated())
//        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }

  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration
  ) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
