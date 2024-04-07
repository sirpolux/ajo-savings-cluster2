package com.cluster2.ajosavingscluster2.security;

import com.cluster2.ajosavingscluster2.configuration.JwtFilterConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

import static org.springframework.http.HttpMethod.*;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityFilterChainConfig {
    private final AuthenticationProvider authenticationProvider;
    private final JwtFilterConfiguration filterConfiguration;
    private final CorsConfigurationSource corsConfigurationSource;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer->
                        configurer
                                .requestMatchers("/api/v1","/api/v1/auth/**","/password/**",
                                        "/v3/api-docs",
                                        "/v3/api-docs",
                                        "/v3/api-docs/**",
                                        "/swagger-resources",
                                        "/swagger-resources/**",
                                        "/configuration/ui",
                                        "/configuration/security",
                                        "/swagger-ui/**",
                                        "/webjars/**",
                                        "/swagger-ui.html")
                                .permitAll()
                                .requestMatchers(POST,"/api/user/create-user", "/api/user/password-reset-request").permitAll()
                                .requestMatchers(POST,"/api/user/login").permitAll()
                                .requestMatchers(GET,"/api/v1/home/**", "/api/kyc/dummy").permitAll()
                                .requestMatchers("/swagger-ui/index.html").permitAll()
                                .requestMatchers(POST,"/api/kyc/update_kyc", "/api/ajo_group/create_ajo_group").hasRole("USER")
                                .requestMatchers(POST, "/api/ajo_group/join","/api/user/credit").hasRole("USER")

                ).sessionManagement((session) ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(filterConfiguration, UsernamePasswordAuthenticationFilter.class);

        http.csrf(csrf->csrf.disable());
        http.cors().configurationSource(corsConfigurationSource);
       // http.cors(corsConfigurationSource-> corsConfigurationSource.disable());
        return http.build();
    }
}


