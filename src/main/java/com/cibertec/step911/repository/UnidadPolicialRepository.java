package com.cibertec.step911.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.step911.entity.UnidadPolicial;

@Repository
public interface UnidadPolicialRepository extends JpaRepository<UnidadPolicial, Integer> {
    
    // Al heredar de JpaRepository, no necesitamos escribir nada más para un CRUD básico.
    // Spring Boot se encarga de crear el código SQL por nosotros de forma invisible.

}