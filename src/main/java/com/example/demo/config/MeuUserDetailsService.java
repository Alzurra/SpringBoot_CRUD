package com.example.demo.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.usuario.Papel;
import com.example.demo.usuario.Usuario;
import com.example.demo.usuario.UsuarioRepositorio;

@Service
public class MeuUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Usuario> usuarioOpcional = this.usuarioRepositorio. findByLogin(username);
		if(usuarioOpcional.isPresent()) {
			
			Usuario usuario = usuarioOpcional.get();
			
			List<GrantedAuthority> autoridades = new ArrayList <>();
			for (Papel papel : usuario.getPapeis()) {
				autoridades.add(new SimpleGrantedAuthority(papel.getNome()));
			}
			
			return new User(username, usuario.getSenha(), autoridades);
			
		}else {
			throw new UsernameNotFoundException("Usuário não encontrado!!!");
		}

	}
}
