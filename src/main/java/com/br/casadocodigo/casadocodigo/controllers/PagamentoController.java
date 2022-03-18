package com.br.casadocodigo.casadocodigo.controllers;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.br.casadocodigo.casadocodigo.classes.CarrinhoCompras;
import com.br.casadocodigo.casadocodigo.classes.Dadospagamento;

@Controller
@RequestMapping("/pagamento")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class PagamentoController {
	@Autowired
	CarrinhoCompras carrinhoCompras;
	@Autowired
	RestTemplate restTemplate;

	@PostMapping("/finalizar")
	public Callable<ModelAndView> finalizar(RedirectAttributes model) {
		return () ->{
			String uri = "http://book-payment.herokuapp.com/payment";
			try {
				String responde = restTemplate.postForObject(uri, new Dadospagamento(carrinhoCompras.getTotal()),
						String.class);
				System.out.println(responde);
				model.addFlashAttribute("sucesso", responde);
				carrinhoCompras.clear();
				return new ModelAndView("redirect:/produtos");
			} catch (HttpClientErrorException e) {
				model.addFlashAttribute("sucesso", "Valor maior que o permitido");
				return new ModelAndView("redirect:/produtos");
			}
		};
		
	}
}
