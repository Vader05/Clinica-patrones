package com.sistemas.edu.restClinica.service;

import java.util.List;

import com.sistemas.edu.restClinica.entity.Paciente;

public interface PacienteService {

	public abstract Paciente addPaciente(Paciente paciente);
	public abstract List<Paciente> listPacientes();
	public abstract Paciente findPacienteById(int id);
	public abstract void removePaciente(int id);
	public abstract long countPacientes();
	public abstract long countPacientesMasculinos();
	public abstract long countPacientesFemenimos();
}
