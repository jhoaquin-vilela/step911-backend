package com.cibertec.step911.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.cibertec.step911.entity.Usuario;
import com.cibertec.step911.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    // ==========================================
    // NUEVO: Obtener los datos del usuario logueado
    // ==========================================
    @GetMapping("/me")
    public ResponseEntity<Usuario> obtenerUsuarioActual(Authentication auth) {
        if (auth == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        
        // auth.getName() nos da el 'username' del que inició sesión
        Optional<Usuario> usuario = service.buscarPorUsername(auth.getName());
        
        return usuario.map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                      .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // ==========================================
    // CRUD NORMAL
    // ==========================================
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return new ResponseEntity<>(service.listarTodos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = service.registrarUsuario(usuario);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        Usuario usuarioActualizado = service.actualizarUsuario(id, usuario);
        if (usuarioActualizado != null) {
            return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Integer id) {
        if (service.eliminarUsuario(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}