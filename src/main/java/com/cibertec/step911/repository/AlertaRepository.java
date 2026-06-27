package com.cibertec.step911.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.step911.entity.Alerta;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Integer> {
    // Listo para manejar todas las operaciones de emergencias
}