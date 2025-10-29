package org.exp.banduapp.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.exp.banduapp.util.Constants.*;

@EnableMethodSecurity
@Configuration
@RequiredArgsConstructor
public class FilterChainConfig {

    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http, JwtFilter mySecurityFilter) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));
        http.authorizeHttpRequests(auth ->
                auth
                        // Public swagger endpoints
                        .requestMatchers(
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // Public auth endpoints
                        .requestMatchers(
                                HttpMethod.POST,
                                API + V1 + AUTH + REGISTER,
                                API + V1 + AUTH + VERIFY + WAY_ALL,
                                API + V1 + AUTH + LOGIN + WAY_ALL
                        ).permitAll()

                        // Public places endpoints
                        .requestMatchers(
                                HttpMethod.GET,
                                API + V1 + PLACES,
                                API + V1 + PLACES + WAY_ONE
                        ).permitAll()

                        // Admin places endpoints
                        .requestMatchers(
                                API + V1 + ADMIN + PLACES + WAY_ALL
                        ).hasRole("ADMIN")

                        // Admin bookings endpoints
                        .requestMatchers(
                                API + V1 + ADMIN + BOOKINGS + WAY_ALL
                        ).hasRole("ADMIN")

                        // Admin users endpoints
                        .requestMatchers(
                                API + V1 + ADMIN + USERS + WAY_ALL
                        ).hasRole("ADMIN")

                        .anyRequest().authenticated()
        );

        http.addFilterBefore(mySecurityFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:3001"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService(customUserDetailsService);
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(authenticationProvider());
    }
}