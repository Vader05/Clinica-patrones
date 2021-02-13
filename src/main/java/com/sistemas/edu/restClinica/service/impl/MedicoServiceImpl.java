package com.sistemas.edu.restClinica.service.impl;

import java.util.List;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sistemas.edu.restClinica.controller.MedicoController;
import com.sistemas.edu.restClinica.entity.Medico;
import com.sistemas.edu.restClinica.entity.Persona;
import com.sistemas.edu.restClinica.repository.MedicoRepository;
import com.sistemas.edu.restClinica.repository.PersonaRepository;
import com.sistemas.edu.restClinica.service.MedicoService;

@Service("medicoServiceImpl")
public class MedicoServiceImpl implements MedicoService {
	
	private static final Log LOG = LogFactory.getLog(MedicoServiceImpl.class);

	
	@Autowired
	@Qualifier("medicoRepository")
	private MedicoRepository medicoRepository;
	
	
	@Autowired
	private PersonaRepository personaRepo;

	@Override
	public Medico addMedico(Medico medico) {
		return medicoRepository.save(medico);
	}

	@Override
	public List<Medico> listMedico() {
		return medicoRepository.findAll();
	}

	@Override
	public Medico findById(int id) {
		
		return medicoRepository.findById(id);
	}

	@Override
	public void removeMedico(int id) {
		medicoRepository.deleteById(id);
	}

	@Override
	public Medico findByPersona(int dni) {
		Medico medico = new Medico();
		Persona persona = personaRepo.findByDNI(dni);
		LOG.info("findBypersona()--------"+dni);
		if(persona!= null) {
			medico = medicoRepository.findByPersona(persona);
			LOG.info("finByPersona()------".concat(medico.toString()));
			return medico;
		}
		return null;
	}

}
