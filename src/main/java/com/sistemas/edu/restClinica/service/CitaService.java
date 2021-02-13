package com.sistemas.edu.restClinica.service;

import java.util.List;

import com.sistemas.edu.restClinica.entity.Citas;
import com.sistemas.edu.restClinica.entity.Medico;

public interface CitaService {
	
	public abstract List<Citas> listCitas();
	public abstract Citas addCita(Citas cita);
	public abstract Citas findById(int id);
	public abstract List<Citas> findByMedico(Medico medico);
	public abstract Citas updateCita(int id, String estado);
	public abstract void removeCita(int id);
	public abstract Citas findByPaciente(int id);
	

}
