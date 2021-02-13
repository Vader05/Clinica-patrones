package com.sistemas.edu.restClinica.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemas.edu.restClinica.entity.Paciente;

@Repository("pacienteRepository")
public interface PacienteRepository extends JpaRepository<Paciente, Serializable> {
	
	public abstract Paciente findById(int id);
	
}
