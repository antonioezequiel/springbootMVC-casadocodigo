package com.br.casadocodigo.casadocodigo.classes;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.br.casadocodigo.casadocodigo.exception.ItemCarrinhoNotFoundException;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class CarrinhoCompras {
	Map<CarrinhoItem, Integer> carrinho = new LinkedHashMap<>();
	
	public Collection<CarrinhoItem> getItens(){
		return carrinho.keySet();
	}

	public void add(CarrinhoItem criaItem) {
		carrinho.put(criaItem, getQuantidade(criaItem));
	}

	private int getQuantidade(CarrinhoItem item) {
		if (!carrinho.containsKey(item))
			return 1;
		else
			return carrinho.get(item) + 1;

	}
	
	public int getQuantidadeTela(CarrinhoItem item) {
		if (!carrinho.containsKey(item))
			return 1;
		else
			return carrinho.get(item);

	}
	public int getQuantidade(){
	    return carrinho.size();
	}
	

	public BigDecimal getTotal(CarrinhoItem item){
	    return item.getTotal(getQuantidadeTela(item));
	}
	
	public BigDecimal getTotal(){
	    BigDecimal total = BigDecimal.ZERO;
	    for (CarrinhoItem item : carrinho.keySet()) {
	        total = total.add(getTotal(item));
	    }
	    return total;
	}

	public boolean removerItem(Integer produtoId, TipoPreco tipo) {
		Produto p = new Produto();
		p.setId(produtoId);
		Integer itemRemovido = carrinho.remove(new CarrinhoItem(p, tipo));
		if (itemRemovido != null) {
			return true;
		}else
			throw new ItemCarrinhoNotFoundException();
		
	}

	public void clear() {
		carrinho.clear();	
		System.out.println("limpou o carrinho");
	}

	public void atualizarItem(Produto produto, TipoPreco tipo, Integer quantidade) {
		//carrinho.remove(new CarrinhoItem(produto, tipo));
		carrinho.put(new CarrinhoItem(produto, tipo), quantidade);		
	}
}
