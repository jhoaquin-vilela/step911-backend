package com.cibertec.step911.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cibertec.step911.entity.Alerta;
import com.cibertec.step911.service.AlertaService;

@RestController
@RequestMapping("/api/alertas")
public class AlertaController {

    @Autowired
    private AlertaService service;

    @GetMapping
    public ResponseEntity<List<Alerta>> listarAlertas() {
        return new ResponseEntity<>(service.listarTodas(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Alerta> registrarAlerta(@RequestBody Alerta alerta) {
        Alerta nuevaAlerta = service.registrarAlerta(alerta);
        return new ResponseEntity<>(nuevaAlerta, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alerta> actualizarAlerta(@PathVariable Integer id, @RequestBody Alerta alerta) {
        Alerta alertaActualizada = service.actualizarAlerta(id, alerta);
        if (alertaActualizada != null) {
            return new ResponseEntity<>(alertaActualizada, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAlerta(@PathVariable Integer id) {
        if (service.eliminarAlerta(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}