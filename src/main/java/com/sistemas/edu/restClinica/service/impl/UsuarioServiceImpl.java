package com.sistemas.edu.restClinica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemas.edu.restClinica.entity.Usuario;
import com.sistemas.edu.restClinica.repository.UsuarioRepository;
import com.sistemas.edu.restClinica.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository userRepo;

	@Override
	public List<Usuario> ListUsuarios() {
		return userRepo.findAll();
	}

	@Override
	public Usuario addUser(Usuario user) {
		return userRepo.save(user);
	}

	@Override
	public void deleteUser(int id) {
		userRepo.deleteById(id);
	}

	@Override
	public Usuario findUserByid(int id) {
		return userRepo.findById(id).orElse(null);
	}
	
	
	
}
