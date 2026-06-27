package com.cibertec.step911.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.cibertec.step911.entity.Usuario;
import com.cibertec.step911.repository.UsuarioRepository;

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
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
    
    // 3. SCRIPT DE ARRANQUE: Creamos un usuario administrador automático si la tabla está vacía
    @Bean
    public CommandLineRunner initAdmin(UsuarioRepository repo, BCryptPasswordEncoder encoder) {
        return args -> {
            if (repo.findByUsername("admin").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setUsername("admin");
                admin.setPasswordHash(encoder.encode("admin123")); // Contraseña encriptada
                admin.setRol("ADMIN");
                admin.setNombreCompleto("Administrador del Sistema");
                admin.setEstadoActivo(true);
                
                repo.save(admin);
                System.out.println("=================================================");
                System.out.println(" USUARIO ADMIN CREADO CON ÉXITO EN LA NUEVA PC");
                System.out.println(" Usuario: admin | Contraseña: admin123");
                System.out.println("=================================================");
            }
        };
    }
}