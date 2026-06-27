package com.cibertec.step911.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.step911.entity.Alerta;
import com.cibertec.step911.repository.AlertaRepository;

@Service
public class AlertaService {

    @Autowired
    private AlertaRepository repository;

    public Alerta registrarAlerta(Alerta alerta) {
        return repository.save(alerta);
    }

    public List<Alerta> listarTodas() {
        return repository.findAll();
    }
    
    public Optional<Alerta> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public Alerta actualizarAlerta(Integer id, Alerta alertaActualizada) {
        if(repository.existsById(id)) {
            alertaActualizada.setIdAlerta(id);
            return repository.save(alertaActualizada);
        }
        return null;
    }

    public boolean eliminarAlerta(Integer id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}