package com.br.casadocodigo.casadocodigo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.casadocodigo.casadocodigo.service.ProdutoService;

@Controller
@RequestMapping("/")
public class HomeController {
	@Autowired
	ProdutoService produtoService;
	
    @GetMapping
    @Cacheable(value = "produtosHome")
    public ModelAndView home() {
    	ModelAndView modelAndView = new ModelAndView("home");    	
    	modelAndView.addObject("produtos", produtoService.getAll());
        return modelAndView;
    }
}
