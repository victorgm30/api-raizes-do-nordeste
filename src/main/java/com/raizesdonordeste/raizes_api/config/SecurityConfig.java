package com.raizesdonordeste.raizes_api.config;

import com.raizesdonordeste.raizes_api.security.JwtFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       
        http
        .csrf(csrf -> csrf.disable())
        .cors(cors -> cors.disable())

            // Configurar a política de criação de sessão para stateless (não guardar estado de sessão)
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) 

            .authorizeHttpRequests(auth -> auth
            // Permitir acesso sem autenticação para endpoints de autenticação e documentação    
            .requestMatchers("/auth/**").permitAll()
            // Permitir acesso sem autenticação para endpoints de documentação (Swagger) 
            .requestMatchers("/swagger-ui/**",
                             "/v3/api-docs/**").permitAll()
            // Exigir autenticação para todas as outras requisições                  
            .anyRequest().authenticated() 
            )

            // Adicionar o filtro JWT antes do filtro de autenticação do Spring Security
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); 

        return http.build();
    }

}
