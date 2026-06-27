package com.cibertec.step911.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.step911.entity.Usuario;
import com.cibertec.step911.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    // C - Crear
    public Usuario registrarUsuario(Usuario usuario) {
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

    // D - Eliminar (En un sistema real, a un usuario se le inactiva, no se le borra, 
    // pero para fines del CRUD académico mantendremos el Delete)
    public boolean eliminarUsuario(Integer id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}