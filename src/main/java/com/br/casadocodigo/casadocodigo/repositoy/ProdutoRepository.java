package com.br.casadocodigo.casadocodigo.repositoy;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.br.casadocodigo.casadocodigo.classes.Produto;
import com.br.casadocodigo.casadocodigo.classes.TipoPreco;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	// busca os preços junto com o produto sem fazer várias consultas, o que não
	// sobrecarrega o banco
	@Query("select distinct (p) from Produto p join fetch p.precos")
	List<Produto> buscaProdutosFecth();

	// busca os preços junto com o produto sem fazer várias consultas, o que não
	// sobrecarrega o banco
	@Query("select p from Produto p join fetch p.precos where p.id = :idProduto")
	Produto buscaProdutosFecthId(int idProduto);

	@Query("select p from Produto p where titulo = :titulo")
	List<Produto> buscaProdutoTitulo(String titulo);
	
	@Query("select sum(preco.valor) from Produto p join p.precos preco where preco.tipo = :tipo")
	BigDecimal somaPrecoPorTipo(TipoPreco tipo);

}
