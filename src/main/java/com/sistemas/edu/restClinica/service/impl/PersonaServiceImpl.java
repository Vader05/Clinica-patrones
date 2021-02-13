package com.sistemas.edu.restClinica.service.impl;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sistemas.edu.restClinica.entity.Persona;
import com.sistemas.edu.restClinica.repository.PersonaRepository;
import com.sistemas.edu.restClinica.service.PersonaService;

@Service("personaServiceImpl")
public class PersonaServiceImpl implements PersonaService {
	
	@Autowired
	@Qualifier("personaRepository")
	private PersonaRepository personaRepository;

	@Override
	@Transactional
	public Persona addPersona(Persona persona) {
		Persona person= personaRepository.save(persona);
		return person;
	}

	@Override
	@Transactional
	public List<Persona> listPersona() {
		return personaRepository.findAll();
	}

	@Override
	public Persona findPersonaById(int id) {
		return personaRepository.findById(id);
	}

	@Override
	@Transactional
	public void removePersona(int id) {
		personaRepository.deleteById(id);
	}

}
