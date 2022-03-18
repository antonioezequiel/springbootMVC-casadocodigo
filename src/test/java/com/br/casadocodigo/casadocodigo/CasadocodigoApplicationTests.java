package com.br.casadocodigo.casadocodigo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.br.casadocodigo.casadocodigo.builder.ProdutoBuilder;
import com.br.casadocodigo.casadocodigo.classes.Produto;
import com.br.casadocodigo.casadocodigo.classes.TipoPreco;
import com.br.casadocodigo.casadocodigo.service.ProdutoService;

@SpringBootTest
@ActiveProfiles("test")
class CasadocodigoApplicationTests {
	
	@Autowired
	ProdutoService produtoService;
	
	@Test
	void validaConsultaSomaProdutoPorTipo() {
		List<Produto> livrosImpressos = ProdutoBuilder.newProduto(TipoPreco.IMPRESSO, BigDecimal.TEN).more(3)
				.buildAll();
		List<Produto> livrosEbook = ProdutoBuilder.newProduto(TipoPreco.EBOOK, BigDecimal.TEN).more(3).buildAll();

		livrosImpressos.stream().forEach(produtoService::create);
		livrosEbook.stream().forEach(p -> produtoService.create(p));
		
		BigDecimal valor = produtoService.somaPrecoPorTipo(TipoPreco.EBOOK);
		assertEquals(new BigDecimal(40).setScale(2), valor);
	}

}
