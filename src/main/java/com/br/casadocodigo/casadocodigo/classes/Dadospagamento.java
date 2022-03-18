package com.br.casadocodigo.casadocodigo.classes;

import java.math.BigDecimal;

public class Dadospagamento {
	private BigDecimal value;

	public Dadospagamento(BigDecimal value) {
		super();
		this.value = value;
	}

	public Dadospagamento() {}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
}
