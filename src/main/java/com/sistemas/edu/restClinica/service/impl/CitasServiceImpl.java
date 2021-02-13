package com.sistemas.edu.restClinica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sistemas.edu.restClinica.entity.Citas;
import com.sistemas.edu.restClinica.entity.Medico;
import com.sistemas.edu.restClinica.repository.CitaRepository;
import com.sistemas.edu.restClinica.service.CitaService;

@Service("citasServiceImpl")
public class CitasServiceImpl implements CitaService {
	
	@Autowired
	@Qualifier("citaRepository")
	private CitaRepository citaRepository;

	@Override
	public List<Citas> listCitas() {
		return citaRepository.findAll();
	}

	@Override
	public Citas addCita(Citas cita) {
		return citaRepository.save(cita);
	}

	@Override
	public Citas findById(int id) {
		
		return citaRepository.findById(id);
	}

	@Override
	public Citas updateCita(int id, String estado) {
		Citas cita=citaRepository.findById(id);
		cita.setEstado(estado);
		
		return citaRepository.save(cita);
	}

	@Override
	public void removeCita(int id) {
		citaRepository.deleteById(id);
	}

	@Override
	public Citas findByPaciente(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Citas> findByMedico(Medico medico) {
		return citaRepository.findByMedico(medico);
	}

}
