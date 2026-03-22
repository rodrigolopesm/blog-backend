package com.example.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuração de segurança do Spring Security.
 *
 * Para um blog público, vamos permitir acesso de leitura sem autenticação.
 * No futuro, quando você quiser adicionar um painel admin para criar posts,
 * você pode proteger endpoints específicos.
 *
 * @Configuration - Diz ao Spring que essa classe tem configurações
 * @EnableWebSecurity - Habilita a configuração customizada de segurança
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Define as regras de segurança da aplicação.
     *
     * @Bean - O Spring gerencia esse objeto (Singleton)
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Desabilita CSRF
            .csrf(csrf -> csrf.disable())

            // LIBERA TUDO - sem autenticação necessária
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()  // Permite acesso a TUDO sem login
            );

        return http.build();
    }
}
