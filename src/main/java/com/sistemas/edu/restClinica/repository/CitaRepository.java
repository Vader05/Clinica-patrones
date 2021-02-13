package com.sistemas.edu.restClinica.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemas.edu.restClinica.entity.Citas;
import com.sistemas.edu.restClinica.entity.Medico;

@Repository("citaRepository")
public interface CitaRepository extends JpaRepository<Citas, Serializable>{
	
	public abstract Citas findById(int id);
	public abstract List<Citas> findByMedico(Medico medico);


}
