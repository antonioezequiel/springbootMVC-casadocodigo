package com.br.casadocodigo.casadocodigo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.br.casadocodigo.casadocodigo.classes.Usuario;
import com.br.casadocodigo.casadocodigo.repositoy.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {
	@Autowired
	UsuarioRepository usuarioRepository;

	public void create(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	public List<Usuario> getAll() {
		return usuarioRepository.findAll();
	}
	@Override
	public Usuario loadUserByUsername(String email) {
		Optional<Usuario> usuario = usuarioRepository.findById(email);
		if (usuario.isPresent())
			return usuario.get();
		else
            throw new UsernameNotFoundException("O usuário " + email + " não foi encontrado");
	}

}
