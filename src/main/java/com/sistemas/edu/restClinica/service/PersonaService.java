package com.sistemas.edu.restClinica.service;

import java.util.List;

import com.sistemas.edu.restClinica.entity.Persona;

public interface PersonaService {
	
	public abstract Persona addPersona(Persona persona);
	public abstract List<Persona> listPersona();
	public abstract Persona findPersonaById(int id);
	public abstract void removePersona(int id);
	

}
