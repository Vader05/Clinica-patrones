package com.sistemas.edu.restClinica.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemas.edu.restClinica.entity.Paciente;
import com.sistemas.edu.restClinica.repository.PacienteRepository;
import com.sistemas.edu.restClinica.service.PacienteService;



@Service("pacienteServiceImpl")
public class PacienteServiceImpl implements PacienteService{
	
	@Autowired()
	private PacienteRepository pacienteRepo;

	@Override
	@Transactional
	public Paciente addPaciente(Paciente paciente) {
		
		return pacienteRepo.save(paciente);
	}

	@Override
	@Transactional
	public List<Paciente> listPacientes() {
		
		return pacienteRepo.findAll();
	}

	@Override
	public Paciente findPacienteById(int id) {
		
		return pacienteRepo.findById(id);
	}

	@Override
	@Transactional
	public void removePaciente(int id) {
		pacienteRepo.deleteById(id);
	}

	@Override
	public long countPacientes() {
		
		return pacienteRepo.count();
	}

	@Override
	public long countPacientesMasculinos() {
		pacienteRepo.count();
		return 0;
	}

	@Override
	public long countPacientesFemenimos() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	

}
