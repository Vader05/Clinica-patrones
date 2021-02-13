package com.sistemas.edu.restClinica.service;

import java.util.List;

import com.sistemas.edu.restClinica.entity.Medico;
import com.sistemas.edu.restClinica.entity.Persona;

public interface MedicoService {
	
	public abstract Medico addMedico(Medico medico);
	public abstract List<Medico> listMedico();
	public abstract Medico findById(int id);
	public abstract void removeMedico(int id);
	public abstract Medico findByPersona(int dni);

}
