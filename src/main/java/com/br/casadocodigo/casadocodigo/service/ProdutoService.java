package com.br.casadocodigo.casadocodigo.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.casadocodigo.casadocodigo.classes.Produto;
import com.br.casadocodigo.casadocodigo.classes.TipoPreco;
import com.br.casadocodigo.casadocodigo.exception.ProdutoNotFoundException;
import com.br.casadocodigo.casadocodigo.repositoy.ProdutoRepository;

@Service
public class ProdutoService {
	ProdutoRepository produtoRepository;
	
	@Autowired
	public ProdutoService(ProdutoRepository produtoRepository) {
		super();
		this.produtoRepository = produtoRepository;
	}

	public void create(Produto produto) {
		produtoRepository.save(produto);
	}

	public List<Produto> getAll() {
		return produtoRepository.buscaProdutosFecth();
		// return produtoRepository.findAll();
	}

	public Produto findByID(Integer id) {
		// Optional<Produto> prod = produtoRepository.findById(id);
		try {
			Optional<Produto> prod = Optional.of(produtoRepository.buscaProdutosFecthId(id));
			if (prod.isPresent())
				return prod.get();
			else
				throw new ProdutoNotFoundException();
		} catch (Exception e) {
			throw new ProdutoNotFoundException();
		}
	}
	
	public BigDecimal somaPrecoPorTipo(TipoPreco tipoPreco) {
		return produtoRepository.somaPrecoPorTipo(tipoPreco);	
	}
}
