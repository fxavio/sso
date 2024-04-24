package org.sso.sso.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeRequests(authorize -> authorize.anyRequest().authenticated())
            .oauth2Login(oauth2 -> oauth2.successHandler(successHandler()))
            .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login"));
    return http.build();
  }

  @Bean
  public AuthenticationSuccessHandler successHandler() {
    SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
    handler.setAlwaysUseDefaultTargetUrl(true);
    handler.setDefaultTargetUrl(System.getenv("DEFAULT_TARGET_URL"));
    return handler;
  }
}