package com.cibertec.step911.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cibertec.step911.entity.TipoIncidente;
import com.cibertec.step911.service.TipoIncidenteService;

@RestController
@RequestMapping("/api/tipos-incidente")
public class TipoIncidenteController {

    @Autowired
    private TipoIncidenteService service;

    // GET: Listar todos los tipos
    @GetMapping
    public ResponseEntity<List<TipoIncidente>> listarTipos() {
        return new ResponseEntity<>(service.listarTodos(), HttpStatus.OK);
    }

    // POST: Registrar nuevo tipo
    @PostMapping
    public ResponseEntity<TipoIncidente> registrarTipo(@RequestBody TipoIncidente tipo) {
        TipoIncidente nuevoTipo = service.registrarTipo(tipo);
        return new ResponseEntity<>(nuevoTipo, HttpStatus.CREATED);
    }

    // PUT: Actualizar tipo
    @PutMapping("/{id}")
    public ResponseEntity<TipoIncidente> actualizarTipo(@PathVariable Integer id, @RequestBody TipoIncidente tipo) {
        TipoIncidente tipoActualizado = service.actualizarTipo(id, tipo);
        if (tipoActualizado != null) {
            return new ResponseEntity<>(tipoActualizado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE: Eliminar tipo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTipo(@PathVariable Integer id) {
        if (service.eliminarTipo(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}