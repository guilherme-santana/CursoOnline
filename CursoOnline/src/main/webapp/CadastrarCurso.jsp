<div id="menu">
	<%@include file="template/menu.jsp"%>
</div>

<section id="entrar" class="container">
	<div class="col-md-12">
		<h2>Cadastrar Curso</h2>
		<h4>Novo Curso</h4>
		
		<c:if test="${errorCadastroCurso != null}">
			<div class="alert alert-warning" role="alert">${errorCadastroCurso}</div>
		</c:if>

		<form method="post" action="Cadastrar">
			<div class="form-group">
				<label for="nome">Nome do Curso:</label>
				<input id="nome" name="nome" class="form-control" type="text" placeholder="Nome do Curso" required="required" maxlength="100">
			</div>

			<div class="form-group">
				<label for="descricao">Descrição do Curso:</label>
	      		<input id="descricao" name="descricao" class="form-control" type="email" placeholder="Descrição do Curso" required="required" maxlength="254">
			</div>
			
			<div class="form-group">
				<label for="cargahoraria">Carga Horária:</label>
				<input id="cargahoraria" name="cargahoraria" type="text" class="form-control" placeholder="Carga Horária" maxlength="3" required="required">
			</div>
			
			<div class="form-group">
				<label for="cargahoraria">Imagem Ilustrativa do Curso:</label>
				<input id="cargahoraria" name="cargahoraria" type="file" class="form-control" maxlength="3" required="required">
			</div>
			
			<div>
				<label for="cadastrar-telefone">Celular:</label>
				<input id="cadastrar-telefone" name="cadastrar-telefone" type="text" class="form-control" placeholder="(99)99999-9999" maxlength="14" required="required">
			</div>
			
			<div class="form-group">
				<label for="cadastrar-senha">Senha:</label>
				<input id="cadastrar-senha" name="cadastrar-senha" class="form-control" type="password" placeholder="********" required="required" maxlength="8">
			</div>

			<button type="submit" class="btn btn-primary">Cadastrar</button>

		</form>
	</div>
</section>

<div id="footer">
	<%@include file="template/footer.jsp"%>	
</div>