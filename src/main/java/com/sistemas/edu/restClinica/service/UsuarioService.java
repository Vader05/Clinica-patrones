package com.sistemas.edu.restClinica.service;

import java.util.List;

import com.sistemas.edu.restClinica.entity.Usuario;

public interface UsuarioService {
	
	public abstract List<Usuario> ListUsuarios();
	
	public abstract Usuario addUser(Usuario user);
	
	public abstract void deleteUser(int id);
	
	public abstract Usuario findUserByid(int id);

}
