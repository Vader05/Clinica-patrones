package com.sistemas.edu.restClinica.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemas.edu.restClinica.entity.Especialidad;

@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad, Serializable> {

	public abstract  Especialidad findByNombre(String name); 
	
}
