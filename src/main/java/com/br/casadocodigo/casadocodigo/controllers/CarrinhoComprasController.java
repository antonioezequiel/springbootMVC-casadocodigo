package com.br.casadocodigo.casadocodigo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.br.casadocodigo.casadocodigo.classes.CarrinhoCompras;
import com.br.casadocodigo.casadocodigo.classes.CarrinhoItem;
import com.br.casadocodigo.casadocodigo.classes.Produto;
import com.br.casadocodigo.casadocodigo.classes.TipoPreco;
import com.br.casadocodigo.casadocodigo.service.ProdutoService;

@RestController
@RequestMapping("/carrinho")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoComprasController {
	@Autowired
	ProdutoService produtoService;
	@Autowired
	CarrinhoCompras carrinhoCompras;
	
	@PostMapping("/add")
	public ModelAndView add(Integer produtoId, TipoPreco tipo) {
		ModelAndView modelAndView = new ModelAndView("redirect:/carrinho");
		carrinhoCompras.add(criaItem(produtoId, tipo));
		return modelAndView;
	}
	
	private CarrinhoItem criaItem(Integer produtoId, TipoPreco tipo){
	    Produto produto = produtoService.findByID(produtoId);
	    CarrinhoItem carrinhoItem = new CarrinhoItem(produto, tipo);
	    return carrinhoItem;
	}
	
	@GetMapping
	public ModelAndView itens(){
	    return new ModelAndView("/carrinho/itens");
	}
	
	@PostMapping("/remover")
	public ModelAndView remover(Integer produtoId, TipoPreco tipo) {
		carrinhoCompras.removerItem(produtoId, tipo );
		return new ModelAndView("/carrinho/itens");
	}
	
	@PostMapping("/atualizar")
	public ModelAndView atualizar(Integer produtoId, TipoPreco tipo, Integer quantidade) {
		Produto produto = produtoService.findByID(produtoId);
		carrinhoCompras.atualizarItem(produto, tipo, quantidade);
		return new ModelAndView("redirect:/carrinho");
	}
}
