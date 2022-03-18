package com.br.casadocodigo.casadocodigo.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.casadocodigo.casadocodigo.classes.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{

}
