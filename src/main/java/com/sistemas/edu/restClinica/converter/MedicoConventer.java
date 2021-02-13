package com.sistemas.edu.restClinica.converter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.sistemas.edu.restClinica.entity.Especialidad;
import com.sistemas.edu.restClinica.entity.Medico;
import com.sistemas.edu.restClinica.entity.Persona;
import com.sistemas.edu.restClinica.model.MedicoModel;
import com.sistemas.edu.restClinica.service.EspecialidadService;
@Component
public class MedicoConventer {
	
	

	
	@Autowired
	private EspecialidadService especialidadService;
	
	public Medico medico2MedicoModel(MedicoModel medicoModel) {
		Medico medico = new Medico();
		Especialidad especialidad = new Especialidad();
		
		especialidad= especialidadService.findEspecilidadByName(medicoModel.getEspecialidad());
		
		//medico.setCitas(medicoModel.getCitas());
		medico.setEspecialidad(especialidad);
		medico.setId(medicoModel.getId());
		
		Persona persona = new Persona();
		persona.setApellido(medicoModel.getApellido());
		persona.setDireccion(medicoModel.getDireccion());
		persona.setDNI(medicoModel.getDni());
		persona.setEmail(medicoModel.getEmail());
		persona.setGenero(medicoModel.getGenero());
		persona.setId(medicoModel.getId());
		persona.setNacimiento(medicoModel.getNacimiento());
		persona.setNombre(medicoModel.getNombre());
		persona.setTelefono(medicoModel.getTelefono());
		persona.setTipo(medicoModel.getTipo());
		
		medico.setPersona(persona);
		return medico;
	}
	
	public MedicoModel medicoModel2Medico(Medico medico) {
		
		MedicoModel medicoModel  = new MedicoModel();
		medicoModel.setApellido(medico.getPersona().getApellido());
		//medicoModel.setCitas(medico.getCitas());
		medicoModel.setDireccion(medico.getPersona().getDireccion());
		medicoModel.setDni(medico.getPersona().getDNI());
		medicoModel.setEmail(medico.getPersona().getEmail());
		medicoModel.setEspecialidad(medico.getEspecialidad().getNombre());
		medicoModel.setGenero(medico.getPersona().getGenero());
		medicoModel.setId(medico.getId());
		medicoModel.setNacimiento(medico.getPersona().getNacimiento());
		medicoModel.setNombre(medico.getPersona().getNombre());
		medicoModel.setTelefono(medico.getPersona().getTelefono());
		medicoModel.setTipo(medico.getPersona().getTipo());
		return medicoModel;
	}

}
