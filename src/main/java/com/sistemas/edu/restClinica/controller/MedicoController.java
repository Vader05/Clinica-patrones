package com.sistemas.edu.restClinica.controller;


import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.sistemas.edu.restClinica.converter.MedicoConventer;
import com.sistemas.edu.restClinica.entity.Citas;
import com.sistemas.edu.restClinica.entity.Medico;
import com.sistemas.edu.restClinica.model.MedicoModel;
import com.sistemas.edu.restClinica.service.MedicoService;

@Controller
@RequestMapping("/medicos")
public class MedicoController {
	
	private static final Log LOG = LogFactory.getLog(MedicoController.class);


	@Autowired
	@Qualifier("medicoServiceImpl")
	private MedicoService medicoService;
	
	@Autowired
	private MedicoConventer medicoConverter;
	

	
	@GetMapping("/list")
	public ModelAndView ListMedico(){
		ModelAndView mov = new ModelAndView("GestionarMedico");
		mov.addObject("medicos", medicoService.listMedico());
		return mov;
	}
	
	@GetMapping("/cancel")
	public String CancelForm() {
		return "redirect:/medicos/list";
	}
	
	 
	@GetMapping("/addmedico")
	public String showForm(@RequestParam(name = "id", required = false) int id, Model model) {
		MedicoModel medicoModel= new MedicoModel();
		if(id!=0) {
			Medico medico = medicoService.findById(id);
			medicoModel = medicoConverter.medicoModel2Medico(medico);
			LOG.info("Method showForm()----".concat(medicoModel.getEspecialidad()));
		}
		model.addAttribute("medicoModel", medicoModel);
		return "RegistrarMedico"; 
	}
	
	@PostMapping("/add")
	public String addMedico(@ModelAttribute(name="medicoModel") MedicoModel medicoModel,Model model) {
		Medico medico = new Medico();
		medico=medicoConverter.medico2MedicoModel(medicoModel);
		
		LOG.info("addMedico()----".concat(medicoModel.getEspecialidad()));
		if(medicoService.addMedico(medico)!=null) {
			model.addAttribute("result", 1);
		}else {
			model.addAttribute("result", 0);
		}
		return "redirect:/medicos/list";
	}
	
	@GetMapping("/removemedico")
	public ModelAndView removeMedico(@RequestParam(name="id" , required = true) int id){
		medicoService.removeMedico(id);
		return ListMedico();
	}
	
	@GetMapping("/reservar")
	public ModelAndView showReservar(){
		ModelAndView mov = new ModelAndView("ReservarCita");
		return mov;
	}
	
	
	@RequestMapping(path="/findmedico/{dni}", method = RequestMethod.POST )
	@ResponseStatus(code = HttpStatus.FOUND)
	public @ResponseBody MedicoModel findById(@PathVariable int dni) {
		MedicoModel medicoModel = new MedicoModel();
		Medico medico = medicoService.findByPersona(dni);
		if(medico!=null) {
			medicoModel= medicoConverter.medicoModel2Medico(medico);
			return  medicoModel;
		}
		return null;
	}
	
	@RequestMapping(value ="/findmedicoby/{dni}", method = RequestMethod.POST)
	public ResponseEntity<Object> findMedico(@PathVariable int dni){
		if(dni==0) {
			LOG.info("DNI de la persona"+dni);
		}
		MedicoModel medicoModel = new MedicoModel();
		Medico medico = medicoService.findByPersona(dni);
		LOG.info("findMedico()------"+dni);
		if(medico!=null) {
			medicoModel = medicoConverter.medicoModel2Medico(medico);
			return new ResponseEntity<Object>(medicoModel, HttpStatus.OK );
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	@GetMapping("/horarioatencion")
	public ModelAndView showAtencion() {
		ModelAndView mov= new ModelAndView("HorarioAtencion");
		return mov;
	}
	
	@GetMapping("/atender")
	public ModelAndView showatenciones() {
		ModelAndView mov= new ModelAndView("AtenderCita");
		return mov;
	}
	
	@GetMapping("/addcita")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Medico addCita(@RequestBody Citas cita ) {
		Medico medico = new Medico();
		medico.setCita(cita);
		return null;
	}
}
