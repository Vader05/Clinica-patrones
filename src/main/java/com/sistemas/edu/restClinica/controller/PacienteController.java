package com.sistemas.edu.restClinica.controller;


import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sistemas.edu.restClinica.converter.PacienteConverter;
import com.sistemas.edu.restClinica.entity.Paciente;

import com.sistemas.edu.restClinica.model.PacienteModel;
import com.sistemas.edu.restClinica.service.PacienteService;


@Controller
@RequestMapping("/pacientes")
public class PacienteController {
	
	private static final Log LOG = LogFactory.getLog(PacienteController.class);

	@Autowired
	private PacienteConverter pacienteConverter;
	
	@Autowired()
	private PacienteService pacienteService;
	
	
	
	@GetMapping("/list")
	public ModelAndView listPacientes() {
		ModelAndView mov = new ModelAndView("GestionarPaciente");
		mov.addObject("pacientes", pacienteService.listPacientes());
		mov.addObject("totalPacientes", pacienteService.countPacientes());
		return mov;
	}
	
	@GetMapping("/cancel")
	public String cancelform() {
		return "redirect:/pacientes/list";
	}
	
	
	@GetMapping("/addpaciente")
	public String showForm(@RequestParam(name="id", required = false) int id, Model model) {
		
		PacienteModel pacienteModel= new PacienteModel();
		
		if(id!=0) {
			Paciente paciente= pacienteService.findPacienteById(id);
			pacienteModel=pacienteConverter.PacienteModelToPaciente(paciente);
			LOG.info("Method addPaciente()---".concat(pacienteModel.getNombre())); 
		}
		
		model.addAttribute("registrarPaciente", "REGISTRO DE PACIENTES");
		model.addAttribute("pacienteModel", pacienteModel);
		return "RegistrarPaciente";
	}
	
	@PostMapping("/add")
	public String addPaciente(@ModelAttribute(name="pacienteModel") PacienteModel pacienteModel,Model model) {		
		Paciente paciente = new Paciente();
		paciente = pacienteConverter.pacienteToPacienteModel(pacienteModel);
		if(pacienteService.addPaciente(paciente)!=null) {
			model.addAttribute("result", 1);
		}else {
			model.addAttribute("result", 0);
		}
		return "redirect:/pacientes/list";
	}
	
	@GetMapping("/removepaciente")
	public ModelAndView removePaciente (@RequestParam(name="id", required= true) int id) {
		pacienteService.removePaciente(id);
		return listPacientes();
	}
	
	@GetMapping("/historial")
	public ModelAndView showHistory() {
		ModelAndView mov = new ModelAndView("HistoriaPaciente");
		return mov;
	}


}
