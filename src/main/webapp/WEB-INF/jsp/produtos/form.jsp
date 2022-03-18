<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Import da taglib -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Livros de Java, Android, iPhone, Ruby, PHP e muito mais -
	Casa do Código</title>
<c:url value="/resources/css-boot" var="cssPath" />
<c:url value="/" var="path" />
<link rel="stylesheet" href="${cssPath}/bootstrap.min.css" />
<link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css" />
<style type="text/css">
body {
	padding-bottom: 60px;
}
</style>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${path}">Casa do Código</a>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="${path }produtos">Lista de Produtos</a></li>
					<li><a href="${s:mvcUrl('PC#form').build()}">Cadastro de
							Produtos</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="${path}logout"><security:authentication
								property="principal" var="usuario"/>
								Usuário: ${usuario.username}</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
	</nav>
	<div class="container">
		<form:form action="${s:mvcUrl('PC#create').build()}" method="POST"
			modelAttribute="produto" enctype="multipart/form-data">
			<div class="form-group">
				<label>Título</label>
				<form:errors path="titulo" cssClass="alert alert-danger" />
				<form:input path="titulo" cssClass="form-control" />
			</div>
			<div class="form-group">
				<label>Descrição</label>
				<form:errors path="descricao" cssClass="alert alert-danger" />
				<form:textarea path="descricao" cssClass="form-control"></form:textarea>
			</div>
			<div class="form-group">
				<label>Páginas</label>
				<form:errors path="paginas" cssClass="alert alert-danger" />
				<form:input path="paginas" cssClass="form-control" />
			</div>
			<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
				<div class="form-group">
					<label>${tipoPreco}</label>
					<form:input path="precos[${status.index}].valor"
						cssClass="form-control" />
					<form:input type="hidden" path="precos[${status.index}].tipo"
						value="${tipoPreco}" />
				</div>
			</c:forEach>
			<div class="form-group">
				<label>Data de Lançamento</label>
				<form:errors path="dataLancamento" />
				<form:input path="dataLancamento" cssClass="form-control" />
			</div>
			<div class="form-group">
				<label>Arquivo</label> <input name="sumario" type="file" />
			</div>
			<button type="submit" class="btn btn-primary">Cadastrar</button>
		</form:form>
	</div>
</body>
</html>