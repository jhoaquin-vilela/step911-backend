package com.cibertec.step911.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cibertec.step911.entity.UnidadPolicial;
import com.cibertec.step911.service.UnidadPolicialService;

@RestController
@RequestMapping("/api/unidades")
public class UnidadPolicialController {

    @Autowired
    private UnidadPolicialService service;

    // GET: Listar todas las patrullas
    @GetMapping
    public ResponseEntity<List<UnidadPolicial>> listarUnidades() {
        return new ResponseEntity<>(service.listarTodas(), HttpStatus.OK);
    }

    // POST: Registrar nueva patrulla
    @PostMapping
    public ResponseEntity<UnidadPolicial> registrarUnidad(@RequestBody UnidadPolicial unidad) {
        UnidadPolicial nuevaUnidad = service.registrarUnidad(unidad);
        return new ResponseEntity<>(nuevaUnidad, HttpStatus.CREATED);
    }

    // PUT: Actualizar patrulla
    @PutMapping("/{id}")
    public ResponseEntity<UnidadPolicial> actualizarUnidad(@PathVariable Integer id, @RequestBody UnidadPolicial unidad) {
        UnidadPolicial unidadActualizada = service.actualizarUnidad(id, unidad);
        if (unidadActualizada != null) {
            return new ResponseEntity<>(unidadActualizada, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE: Eliminar patrulla
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUnidad(@PathVariable Integer id) {
        if (service.eliminarUnidad(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}