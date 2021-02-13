package com.sistemas.edu.restClinica.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemas.edu.restClinica.entity.Persona;

@Repository("personaRepository")
public interface PersonaRepository extends JpaRepository<Persona, Serializable>{
	
	public abstract Persona findById(int id);
	public abstract Persona findByDNI(int dni);

}
