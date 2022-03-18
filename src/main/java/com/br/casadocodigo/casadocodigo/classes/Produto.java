package com.br.casadocodigo.casadocodigo.classes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty(message = "{titulo.notEmpty}")
	private String titulo;
	@NotEmpty(message = "{descricao.notEmpty}")
	private String descricao;
	@NotNull(message = "{paginas.notNull}")
	@Min(value = 1, message = "{paginas.min}")
	private Integer paginas;
	@ElementCollection
	private List<Preco> precos = new ArrayList<Preco>();
	@NotNull(message = "{data.notEmpty}")
	private Calendar dataLancamento;
	private String sumarioPath;
	
	
	public Produto(Integer id, String titulo, String descricao, int paginas, List<Preco> precos) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.paginas = paginas;
		this.precos = precos;
	}
	
	public Produto() {}
	
	public String getSumarioPath() {
		return sumarioPath;
	}

	public void setSumarioPath(String sumarioPath) {
		this.sumarioPath = sumarioPath;
	}

	public Calendar getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Calendar dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Preco> getPrecos() {
		return precos;
	}

	public void setPrecos(List<Preco> precos) {
		this.precos = precos;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getPaginas() {
		return paginas;
	}
	public void setPaginas(Integer paginas) {
		this.paginas = paginas;
	}
	@Override
	public String toString() {
		return "Produto [titulo=" + titulo + ", descricao=" + descricao + ", paginas=" + paginas + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}
	
	public BigDecimal precoPara(TipoPreco tipoPreco) {
	    return precos.stream().filter(preco -> preco.getTipo().equals(tipoPreco)).findFirst().get().getValor();        
	}
}
