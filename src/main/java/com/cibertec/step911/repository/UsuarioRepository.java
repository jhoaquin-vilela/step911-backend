package com.cibertec.step911.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.step911.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
    // Método personalizado o "Query Method". 
    // Spring Boot construirá automáticamente el "SELECT * FROM usuarios WHERE username = ?"
    // Esto será vital cuando configuremos el Login con Spring Security.
    Optional<Usuario> findByUsername(String username);
    
}