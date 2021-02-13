package com.sistemas.edu.restClinica.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemas.edu.restClinica.entity.Medico;
import com.sistemas.edu.restClinica.entity.Persona;

@Repository("medicoRepository")
public interface MedicoRepository  extends JpaRepository<Medico, Serializable>{
	
	public abstract Medico findById(int id);
	public abstract Medico findByPersona(Persona persona);
	
}
