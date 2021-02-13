package com.sistemas.edu.restClinica.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping
public class LoginController {
	


	/*
	@GetMapping("/")
	public String redirectToLogin() {
		LOG.info("METHOD: redirectToLogin()");
		return "redirect:/login";
	}
	*/
	@GetMapping("/login")
	public String ShowLoginForm(@RequestParam(value="error", required = false) String error,
			@RequestParam(value="logout", required = false) String logout,
			Model model, Principal principal, RedirectAttributes flash ) {
		
		if(principal!=null) {
			flash.addFlashAttribute("info", "Usted ya ha iniciado sesion anteriormente");
			return "redirect:/";
		}
		if(error!=null) {
			model.addAttribute("error", "usuario o contraseña invalidos");
		}
		
		if(logout!=null) {
			model.addAttribute("logout", "ha cerrado sesion con exito");
		}
		return "login";
	}
	
	/*
	@PostMapping("/logincheck")
	public String LoginCheck(@ModelAttribute(name="usuario")Usuario user ) {
		Usuario usuario=userService.findUserByEmail(user.getEmail()); 
		LOG.info("METHOD: LoginCheck()-- params"+user.toString());

		if(usuario!=null) {
			if(user.getPassword().equals(usuario.getPassword())){
				return "redirect:/pacientes/list";
			}
		}
		return "redirect:/login?error";
	}
	*/
}
