package com.duoc.learningplatform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {
        return http
            .authorizeHttpRequests(auth -> auth
                // H2 console: libre para desarrollo
                .requestMatchers("/h2-console/**").permitAll()
                // Cursos activos: publico
                .requestMatchers("/api/cursos/activos").permitAll()
                // Usuarios: solo ADMIN
                .requestMatchers("/api/usuarios/**").hasRole("ADMIN")
                // Cursos (crear/editar): PROFESOR y ADMIN
                .requestMatchers("/api/cursos/**").hasAnyRole("PROFESOR", "ADMIN")
                // Todo lo demas: requiere login
                .anyRequest().authenticated()
            )
            // Config especial para H2
            .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
            .headers(h -> h.frameOptions(f -> f.sameOrigin()))
            .formLogin(withDefaults())
            .logout(logout -> logout.logoutSuccessUrl("/api/cursos/activos"))
            .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}