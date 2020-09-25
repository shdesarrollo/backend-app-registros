package com.example.registros.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.registros.entitys.Registro;

public interface RegistrosDAO extends JpaRepository<Registro, Long> {

}
