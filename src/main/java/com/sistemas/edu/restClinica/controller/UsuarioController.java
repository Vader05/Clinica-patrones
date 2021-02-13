package com.sistemas.edu.restClinica.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sistemas.edu.restClinica.entity.Role;
import com.sistemas.edu.restClinica.entity.Usuario;
import com.sistemas.edu.restClinica.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	private static final Log LOG = LogFactory.getLog(UsuarioController.class);

	@Autowired
	private UsuarioService userService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	



	@GetMapping("/list")
	public ModelAndView listUsuarios() {
		ModelAndView mov = new ModelAndView("ListaUsuarios");
		List<Usuario> usuarios = userService.ListUsuarios();
		
		mov.addObject("usuarios", usuarios);
		return mov;
	}

	@GetMapping("/admin")
	public String showForm(@RequestParam(name = "id", required = false) int id, Model model) {

		Usuario usuario= new Usuario();

		if (id != 0) {
			usuario= userService.findUserByid(id);
			
			LOG.info("Method addUsername()---".concat(usuario.getUsername()));
		}

		model.addAttribute("usuario", usuario);
		return "UserAdmin";
	}
	
	
	@PostMapping("/addadmin")
	public String addAdmin(@ModelAttribute(name="usuario") Usuario user, Model model) {
		Usuario usuario=new Usuario();
		List<Role> roles = new ArrayList<Role>();
		Role rol = new Role();
		if(user!=null) {
		rol.setAuthority("ROLE_ADMIN");
		roles.add(rol);
		user.setRoles(roles);
		usuario=user;
		usuario.setPassword(passwordEncoder.encode(user.getPassword()));
		usuario.setEnabled(user.getEnabled());
		}
		if(userService.addUser(usuario)!=null) {
			model.addAttribute("result", 1);
		}else {
			model.addAttribute("result", 0);
		}
		return "redirect:/usuarios/list";
		
	}
	
	@GetMapping("/removeuser")
	public ModelAndView removePaciente (@RequestParam(name="id", required= true) int id) {
		userService.deleteUser(id);
		return listUsuarios();
	}
	
	@GetMapping("/medico")
	public String showFormMedico(@RequestParam(name = "id", required = false) int id, Model model) {

		Usuario usuario= new Usuario();

		if (id != 0) {
			usuario= userService.findUserByid(id);
			
			LOG.info("Method addUsername()---".concat(usuario.getUsername()));
		}

		model.addAttribute("usuario", usuario);
		return "UserMedico";
	}
	
	@PostMapping("/addmedico")
	public String addMedico(@ModelAttribute(name="usuario") Usuario user, Model model) {
		Usuario usuario=new Usuario();
		List<Role> roles = new ArrayList<Role>();
		Role rol = new Role();
		if(user!=null) {
		rol.setAuthority("ROLE_MEDICO");
		roles.add(rol);
		user.setRoles(roles);
		usuario=user;
		usuario.setPassword(passwordEncoder.encode(user.getPassword()));
		usuario.setEnabled(user.getEnabled());
		}
		if(userService.addUser(usuario)!=null) {
			model.addAttribute("result", 1);
		}else {
			model.addAttribute("result", 0);
		}
		return "redirect:/usuarios/list";
		
	}
	
	

}
