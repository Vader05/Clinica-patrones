package com.sistemas.edu.restClinica.converter;

import org.springframework.stereotype.Component;

import com.sistemas.edu.restClinica.entity.Paciente;
import com.sistemas.edu.restClinica.entity.Persona;
import com.sistemas.edu.restClinica.model.PacienteModel;

@Component
public class PacienteConverter {
	
	public Paciente pacienteToPacienteModel(PacienteModel pacienteModel) {
		Paciente paciente = new Paciente(); 
		paciente.setAlergias(pacienteModel.getAlergias());
		paciente.setTalla(pacienteModel.getTalla());
		paciente.setTipoSangre(pacienteModel.getTipoSangre());
		paciente.setPeso(pacienteModel.getPeso());
		paciente.setId(pacienteModel.getId());
		
		Persona persona = new Persona();

		persona.setTipo("paciente");
		persona.setApellido(pacienteModel.getApellido());
		persona.setDireccion(pacienteModel.getDireccion());
		persona.setDNI(pacienteModel.getDni());
		persona.setEmail(pacienteModel.getEmail());
		persona.setGenero(pacienteModel.getGenero());
		
		persona.setNombre(pacienteModel.getNombre());
		persona.setNacimiento(pacienteModel.getNacimiento());
		persona.setTelefono(pacienteModel.getTelefono());
		
		paciente.setPersona(persona);
		
		return paciente;
	}
	
	public PacienteModel PacienteModelToPaciente(Paciente paciente) {
		PacienteModel pacienteModel= new PacienteModel();
		
		pacienteModel.setAlergias(paciente.getAlergias());
		pacienteModel.setApellido(paciente.getPersona().getApellido());
		pacienteModel.setDireccion(paciente.getPersona().getDireccion());
		pacienteModel.setDni(paciente.getPersona().getDNI());
		pacienteModel.setEmail(paciente.getPersona().getEmail());
		pacienteModel.setGenero(paciente.getPersona().getGenero());
		pacienteModel.setId(paciente.getId());
		pacienteModel.setNacimiento(paciente.getPersona().getNacimiento());
		pacienteModel.setNombre(paciente.getPersona().getNombre());
		pacienteModel.setPeso(paciente.getPeso());
		pacienteModel.setTalla(paciente.getTalla());
		pacienteModel.setTelefono(paciente.getPersona().getTelefono());
		pacienteModel.setTipoSangre(paciente.getTipoSangre());
		pacienteModel.setTipo(paciente.getPersona().getTipo());
		pacienteModel.setCitas(paciente.getCitas());
		
		return pacienteModel;
		
		
	}
	

}
