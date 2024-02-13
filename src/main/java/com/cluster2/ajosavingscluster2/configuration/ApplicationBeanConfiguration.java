package com.cluster2.ajosavingscluster2.configuration;

import com.cluster2.ajosavingscluster2.mapper.AjoGroupMapper;
import com.cluster2.ajosavingscluster2.mapper.KycMapper;
import com.cluster2.ajosavingscluster2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationBeanConfiguration {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
   public UserDetailsService userDetailsService(){
        return username -> userRepository.findByUsernameIgnoreCase(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return daoAuthenticationProvider;

    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AjoGroupMapper ajoGroupMapper(){
        return new AjoGroupMapper();
    }

    @Bean
    public KycMapper kycMapper(){
        return new KycMapper();
    }
}
