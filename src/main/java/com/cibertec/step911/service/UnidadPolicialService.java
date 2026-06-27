package com.cibertec.step911.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cibertec.step911.entity.UnidadPolicial;
import com.cibertec.step911.repository.UnidadPolicialRepository;

@Service
public class UnidadPolicialService {

    @Autowired
    private UnidadPolicialRepository repository;

    // C - Crear
    public UnidadPolicial registrarUnidad(UnidadPolicial unidad) {
        return repository.save(unidad);
    }

    // R - Listar todas
    public List<UnidadPolicial> listarTodas() {
        return repository.findAll();
    }
    
    // R - Buscar por ID
    public Optional<UnidadPolicial> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    // U - Actualizar
    public UnidadPolicial actualizarUnidad(Integer id, UnidadPolicial unidadActualizada) {
        if(repository.existsById(id)) {
            unidadActualizada.setIdUnidad(id);
            return repository.save(unidadActualizada);
        }
        return null;
    }

    // D - Eliminar
    public boolean eliminarUnidad(Integer id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}