package com.cibertec.step911.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.cibertec.step911.entity.Usuario;
import com.cibertec.step911.repository.UsuarioRepository;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 1. Configuramos el motor de Encriptación (BCrypt)
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. Configuramos las reglas de acceso
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .requestMatchers("/api/**").authenticated()
                .anyRequest().permitAll()
            )
            // Modificado: Bloqueamos la ventanita gris del navegador
            .httpBasic(basic -> basic
                .authenticationEntryPoint((request, response, authException) -> 
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No Autorizado")
                )
            );

        return http.build();
    }
    
    // 3. SCRIPT DE ARRANQUE (Seeding): Creamos un usuario administrador automático por defecto
    @Bean
    public CommandLineRunner initAdmin(UsuarioRepository repo, BCryptPasswordEncoder encoder) {
        return args -> {
            // Buscamos si ya existe el usuario "admin"
            if (repo.findByUsername("admin").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setUsername("admin");
                // ¡Aquí está la magia! Encriptamos la contraseña antes de guardarla en PostgreSQL
                admin.setPasswordHash(encoder.encode("admin123"));
                admin.setRol("ADMIN");
                admin.setNombreCompleto("Administrador del Sistema");
                admin.setEstadoActivo(true);
                
                repo.save(admin);
                System.out.println("=================================================");
                System.out.println(" USUARIO ADMIN CREADO CON ÉXITO");
                System.out.println(" Usuario: admin | Contraseña: admin123");
                System.out.println("=================================================");
            }
        };
    }
}