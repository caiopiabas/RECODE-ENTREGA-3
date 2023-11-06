<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Usuarios</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
</head>
<body>

	<!-- menu principal -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light"
		style="height: 80px;">
		<div class="container">
			<a class="navbar-brand" href="#"> <img
				src="./assets/images/logo.gif" alt="logo" class="img-fluid"
				width="100" height="80">
			</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link" aria-current="page"
						href="index.html">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="destino.html">Destino</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="promocoes.html">Promoções</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="contato.html">Contato</a>
					</li>
				</ul>

			</div>
		</div>
	</nav>

	<div class="text-center">
		<h3>Usuarios</h3>
	</div>

	<div class="container">


		<div class="container mt-5 border border-2 border-secondary">
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-primary mt-3" data-bs-toggle="modal"
				data-bs-target="#CadastroModal">Cadastrar Usuario</button>

			<!-- Modal -->
			<div class="modal fade" id="CadastroModal" tabindex="-1"
				aria-labelledby="CadastroModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h1 class="modal-title fs-5" id="CadastroModalLabel">Cadastro
								Usuario</h1>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>

						<div class="modal-body">
							<form action="usuario-save">
								<div class="form-group">
									<label for="nome">Nome:</label> <input type="text"
										class="form-control" id="nome" name="nome" placeholder="Nome">
								</div>
								<div class="form-group">
									<label for="email">Email:</label> <input type="email"
										class="form-control" id="email" name="email"
										placeholder="Email">
								</div>
								<div class="mt-3">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">Sair</button>
									<button type="submit" class="btn btn-primary">Salvar</button>

								</div>
							</form>
						</div>


					</div>
				</div>
			</div>
			<div class="table-responsive">

				<table class="table">
					<thead>
						<tr>
							<th scope="col">Id</th>
							<th scope="col">Nome</th>
							<th scope="col">Email</th>
							<th scope="col">Ações</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${usuarios }" var="u">
							<tr>
								<th scope="row">${u.id }</th>
								<td>${u.nome}</td>
								<td>${u.email}</td>
								<td><a class="btn btn-warning" data-bs-toggle="modal"
									data-bs-target="#AtualizarModal${u.id }">Editar</a> <a
									class="btn btn-danger" data-bs-toggle="modal"
									data-bs-target="#ExcluirModal${u.id }">Excluir</a></td>
							</tr>

							<div class="modal fade" id="ExcluirModal${u.id }" tabindex="-1"
								aria-labelledby="exampleModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title">Excluir Usuario</h5>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body">
											<p>Atenção! Voce esta preste a excluir um cadastro.</p>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">Sair</button>
											<a href="usuario-delete?id=${u.id }" class="btn btn-primary">Excluir</a>
										</div>
									</div>
								</div>
							</div>
							<!-- Modal -->
							<div class="modal fade" id="AtualizarModal${u.id }" tabindex="-1"
								aria-labelledby="exampleModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h1 class="modal-title fs-5" id="exampleModalLabel">Atualizar
												Usuario</h1>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="close"></button>
										</div>

										<div class="modal-body">
											<form action="usuario-save">
												<input type="text" hidden class="form-control" id="id"
													name="id" placeholder="Id" value="${u.id }">
												<div class="form-group">
													<label for="nome">Nome:</label> <input type="text"
														class="form-control" id="nome" name="nome"
														placeholder="Nome" value="${u.nome }">
												</div>
												<div class="form-group">
													<label for="email">Email:</label> <input type="email"
														class="form-control" id="email" name="email"
														placeholder="Email" value="${u.email }">
												</div>
												<div class="mt-3">
													<button type="button" class="btn btn-secondary"
														data-bs-dismiss="modal">Sair</button>
													<button type="submit" class="btn btn-primary">Salvar</button>

												</div>
											</form>
										</div>


									</div>
								</div>
							</div>
						</c:forEach>

					</tbody>
				</table>








			</div>
		</div>
	</div>

	<!-- Rodapé -->
	<footer class="mt-4 bg-primary text-center text-white p-1 fixed-bottom">
		<p>
			<i class="bi bi-c-circle" style="font-size: 1rem"></i> Todos os
			direitos reservados à agência de viagens Giramundo.
		</p>
	</footer>

	<!-- Scripts -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"
		integrity="sha384-KyZXEAg3QhqLMpG8r+O3+XpzrW8p7l2K6n6a4ssYbF4g+pB1z4aO1bN6Lq3qNXdq7"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>

</body>
</html>