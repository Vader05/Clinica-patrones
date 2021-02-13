package com.sistemas.edu.restClinica.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.sistemas.edu.restClinica.entity.Role;
import com.sistemas.edu.restClinica.entity.Usuario;
import com.sistemas.edu.restClinica.repository.UsuarioRepository;
@Service("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
	
	private static final Log LOG = LogFactory.getLog(UserDetailsService.class);


	@Autowired
	private UsuarioRepository usuarioRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario= usuarioRepo.findByUsername(username);
		
		if(usuario==null) {
			LOG.error("Error Login: no existe el usuario ".concat(username));
			throw new UsernameNotFoundException("Username".concat(username)+" no existe en el sistema");
		}
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(Role role : usuario.getRoles()) {
			LOG.info("Roles del usuario ".concat(role.getAuthority()));
			authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
		}
		if(authorities.isEmpty()) {
			LOG.error("Error Login: el usuario ".concat(username)+" no tiene roles asignados");
			throw new UsernameNotFoundException("Error Login: el usuario ".concat(username)+" no tiene roles asignados");
		}
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}

}
