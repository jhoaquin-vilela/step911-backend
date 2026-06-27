package com.cibertec.step911.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cibertec.step911.entity.Usuario;
import com.cibertec.step911.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    // MAGIA DE SEGURIDAD: Inyectamos el encriptador de Spring Security
    @Autowired
    private PasswordEncoder passwordEncoder;

    // C - Crear (CON ENCRIPTACIÓN REAL BCRYPT)
    public Usuario registrarUsuario(Usuario usuario) {
        // 1. Tomamos la contraseña en texto plano que llega desde tu página web
        // 2. La encriptamos con BCrypt
        String claveEncriptada = passwordEncoder.encode(usuario.getPasswordHash());
        
        // 3. Reemplazamos la contraseña plana por la encriptada
        usuario.setPasswordHash(claveEncriptada);
        
        // 4. Guardamos el usuario blindado en PostgreSQL
        return repository.save(usuario);
    }

    // R - Listar todos
    public List<Usuario> listarTodos() {
        return repository.findAll();
    }
    
    // R - Buscar por ID
    public Optional<Usuario> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    // R - Buscar por Username
    public Optional<Usuario> buscarPorUsername(String username) {
        return repository.findByUsername(username);
    }

    // U - Actualizar
    public Usuario actualizarUsuario(Integer id, Usuario usuarioActualizado) {
        if(repository.existsById(id)) {
            usuarioActualizado.setIdUsuario(id);
            return repository.save(usuarioActualizado);
        }
        return null;
    }

    // D - Eliminar
    public boolean eliminarUsuario(Integer id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}