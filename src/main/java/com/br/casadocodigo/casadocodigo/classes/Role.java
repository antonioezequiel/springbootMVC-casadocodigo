package com.br.casadocodigo.casadocodigo.classes;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(unique = true, length = 50)
	private String nome;
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Usuario> usuario;

	@Override
	public String getAuthority() {
		return this.nome;
	}
	
	public Role(String nome, List<Usuario> usuario) {
		super();
		this.nome = nome;
		this.usuario = usuario;
	}
	
	public Role() {	}
	
	public String getNome() {
		return nome;
	}

	public List<Usuario> getUsuario() {
		return usuario;
	}

	public void setUsuario(List<Usuario> usuario) {
		this.usuario = usuario;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	
}
