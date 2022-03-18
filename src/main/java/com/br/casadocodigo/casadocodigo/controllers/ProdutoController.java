package com.br.casadocodigo.casadocodigo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Scope;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.casadocodigo.casadocodigo.classes.CarrinhoCompras;
import com.br.casadocodigo.casadocodigo.classes.Produto;
import com.br.casadocodigo.casadocodigo.classes.TipoPreco;
import com.br.casadocodigo.casadocodigo.exception.ProdutoNotFoundException;
import com.br.casadocodigo.casadocodigo.infra.FileSaver;
import com.br.casadocodigo.casadocodigo.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class ProdutoController {
	@Autowired
	FileSaver fileSaver;
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	CarrinhoCompras carrinhoCompras;

	@RequestMapping("/form")
	public ModelAndView form(Produto produto) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("tipos", TipoPreco.values());
		modelAndView.setViewName("produtos/form");
		return modelAndView;
	}

	@PostMapping
	@CacheEvict(value = "produtosHome",  allEntries=true)
	public ModelAndView create(MultipartFile sumario, @Valid Produto produto, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			return form(produto);
		}
		
		String path = fileSaver.write("arquivos-sumario", sumario);
		produto.setSumarioPath(path);
		produtoService.create(produto);

		redirectAttributes.addFlashAttribute("sucesso", "produto cadastrado com Sucesso!");
		return listar();

	}

	@GetMapping
	public ModelAndView listar() {
		List<Produto> produtos = produtoService.getAll();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("produtos/lista");
		modelAndView.addObject("produtos", produtos);
		return modelAndView;
	}
	
	@GetMapping("/json")
	public List<Produto> listarJson() {
		List<Produto> produtos = produtoService.getAll();
		return produtos;
	}
	
	@GetMapping("/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable("id") Integer id) {
		/*
		 * Só para testes de tratamento de erro
		 */
		//if(id != 2) throw new RuntimeException();
		
		ModelAndView modelAndView = new ModelAndView("produtos/detalhe");
		modelAndView.addObject("produto", produtoService.findByID(id));
		modelAndView.addObject("quantidade", carrinhoCompras.getQuantidade());
		return modelAndView;
	}
	
	@GetMapping("/{id}")
	public Produto detalheJson(@PathVariable("id") Integer id) {
		return produtoService.findByID(id);
		
	}
	
	@ExceptionHandler(ProdutoNotFoundException.class)
	public ModelAndView erroProduto() {
		ModelAndView modelAndView = new ModelAndView("erroProduto");
		return modelAndView;
	}
}
