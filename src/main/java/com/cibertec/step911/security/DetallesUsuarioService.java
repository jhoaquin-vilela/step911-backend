package com.cibertec.step911.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cibertec.step911.entity.Usuario;
import com.cibertec.step911.repository.UsuarioRepository;

@Service
public class DetallesUsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscamos al usuario en nuestra base de datos real (PostgreSQL)
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        // Convertimos nuestro "Usuario" al "UserDetails" que entiende Spring Security
        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPasswordHash()) // Debe ser una contraseña encriptada con BCrypt
                .roles(usuario.getRol()) // Ejemplo: "OPERADOR" o "DESPACHADOR"
                .build();
    }
}