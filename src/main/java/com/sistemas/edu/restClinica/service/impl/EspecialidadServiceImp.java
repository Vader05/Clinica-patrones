package com.sistemas.edu.restClinica.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemas.edu.restClinica.entity.Especialidad;
import com.sistemas.edu.restClinica.repository.EspecialidadRepository;
import com.sistemas.edu.restClinica.service.EspecialidadService;

@Service
public class EspecialidadServiceImp implements EspecialidadService {
	
	@Autowired
	private EspecialidadRepository especialidadRepo; 

	@Override
	public Especialidad findEspecilidadByName(String name) {
		
		Especialidad especialidad = new Especialidad();
		especialidad= especialidadRepo.findByNombre(name);
		return especialidad;
	}

}
