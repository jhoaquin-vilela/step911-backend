package com.cibertec.step911.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.step911.entity.TipoIncidente;

@Repository
public interface TipoIncidenteRepository extends JpaRepository<TipoIncidente, Integer> {
    // Heredamos los métodos básicos del CRUD
}