package com.br.casadocodigo.casadocodigo.classes;

import java.math.BigDecimal;
import java.util.Objects;

public class CarrinhoItem {
	private Produto produto;
	private TipoPreco tipo;

	public CarrinhoItem() {	}
	
	public CarrinhoItem(Produto produto, TipoPreco tipo) {
		super();
		this.produto = produto;
		this.tipo = tipo;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public TipoPreco getTipo() {
		return tipo;
	}

	public void setTipo(TipoPreco tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(produto, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarrinhoItem other = (CarrinhoItem) obj;
		return Objects.equals(produto, other.produto) && tipo == other.tipo;
	}
	
	public BigDecimal getPreco(){
	    return produto.precoPara(tipo);
	}
	
	public BigDecimal getTotal(int quantidade) {
	    return this.getPreco().multiply(new BigDecimal(quantidade));
	}
}
