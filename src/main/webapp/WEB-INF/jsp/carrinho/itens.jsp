
<%@ include file="/WEB-INF/jsp/cabecalho.jsp"%>

<section class="container middle">
	<h2 id="cart-title">Seu carrinho de compras</h2>


	<table id="cart-table">
		<colgroup>
			<col class="item-col" />
			<col class="item-price-col" />
			<col class="item-quantity-col" />
			<col class="line-price-col" />
			<col class="delete-col" />
		</colgroup>
		<thead>
			<tr>
				<th class="cart-img-col"></th>
				<th width="65%">Item</th>
				<th width="10%">Preço</th>
				<th width="10%">Quantidade</th>
				<th width="10%">Total</th>
				<th width="5%"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${carrinhoCompras.itens }" var="item">
				<tr>
					<td class="cart-img-col"><img
						src="https://images-na.ssl-images-amazon.com/images/I/517KvLLwZXL._SY344_BO1,204,203,200_QL70_ML2_.jpg"
						width="71px" height="100px" /></td>
					<td class="item-title">${item.produto.titulo}-${item.tipo}</td>
					<td class="numeric-cell">${item.preco}</td>
					<td class="quantity-input-cell"><form:form
							servletRelativeAction="carrinho/atualizar" method="post">
							<input type="hidden" name="produtoId" value="${item.produto.id}">
							<input type="hidden" name="tipo" value="${item.tipo}">
							<input type="number" min="0" id="quantidade" name="quantidade"
								value="${carrinhoCompras.getQuantidadeTela(item) }" />
							<input type="image"
								src="${contextPath}resources/imagens/atualizar.jpg"
								style="height: 20px; width: 20px" alt="atualizar"
								title="atualizar" />
						</form:form></td>
					<td class="numeric-cell">${carrinhoCompras.getTotal(item)}</td>
					<td>
					<td><form:form
							servletRelativeAction="${s:mvcUrl('CCC#remover').arg(0, item.produto.id).arg(1,item.tipo).build()}"
							method="post">
							<input type="image"
								src="${contextPath}resources/imagens/excluir.png" alt="Excluir"
								title="Excluir" />
						</form:form></td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="3">
					<form action="${contextPath}pagamento/finalizar" method="post">
						<button type="submit" class="formularioDoCarrinho-finalizar-botao"
							name="checkout">
							Finalizar <span
								class="formularioDoCarrinho-finalizar-botao-texto"
								role="presentation"> compra</span>
						</button>
					</form>
				</td>
				<td class="numeric-cell">${carrinhoCompras.total}</td>
				<td></td>
			</tr>
		</tfoot>
	</table>

	<h2>Você já conhece os outros livros da Casa do Código?</h2>
	<ul id="collection" class="related-books">
		<li class="col-left"><a href="/products/livro-plsql"
			class="block clearfix book-suggest"
			data-book="PL/SQL: Domine a linguagem do banco de dados Oracle">
				<img width="113px" height="160px"
				src="https://images-na.ssl-images-amazon.com/images/I/517KvLLwZXL._SY344_BO1,204,203,200_QL70_ML2_.jpg"
				alt="PL/SQL: Domine a linguagem do banco de dados Oracle" />
		</a></li>
	</ul>

	<h2>
		<a href="http://www.casadocodigo.com.br">Veja todos os livros que
			publicamos!</a>
	</h2>
</section>



<%@ include file="/WEB-INF/jsp/footer.jsp"%>