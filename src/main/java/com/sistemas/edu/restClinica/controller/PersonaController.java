package com.sistemas.edu.restClinica.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.servlet.ModelAndView;


import com.sistemas.edu.restClinica.service.PersonaService;

@Controller
public class PersonaController {

	@Autowired
	@Qualifier("personaServiceImpl")
	private PersonaService personaService;
	
	@GetMapping("/")
	public ModelAndView showPacientes() {
		ModelAndView mov = new ModelAndView("home");
		return mov;
	}
	
}
