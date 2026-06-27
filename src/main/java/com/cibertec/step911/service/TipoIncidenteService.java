package com.cibertec.step911.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.step911.entity.TipoIncidente;
import com.cibertec.step911.repository.TipoIncidenteRepository;

@Service
public class TipoIncidenteService {

    @Autowired
    private TipoIncidenteRepository repository;

    // C - Crear
    public TipoIncidente registrarTipo(TipoIncidente tipo) {
        return repository.save(tipo);
    }

    // R - Listar todos
    public List<TipoIncidente> listarTodos() {
        return repository.findAll();
    }
    
    // R - Buscar por ID
    public Optional<TipoIncidente> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    // U - Actualizar
    public TipoIncidente actualizarTipo(Integer id, TipoIncidente tipoActualizado) {
        if(repository.existsById(id)) {
            tipoActualizado.setIdTipo(id);
            return repository.save(tipoActualizado);
        }
        return null;
    }

    // D - Eliminar
    public boolean eliminarTipo(Integer id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}