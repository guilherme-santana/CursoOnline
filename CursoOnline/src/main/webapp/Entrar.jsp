<div id="menu">
	<%@include file="template/menu.jsp"%>
</div>

<section id="entrar" class="container">
	<div class="col-md-6">
		<h2>Entrar</h2>
		<h4>Já sou usuário</h4>
		
		<c:if test="${errorMessage != null}">
			<div class="alert alert-warning" role="alert">${errorMessage}</div>
		</c:if>
		
		<form method="post" action="EntrarLogin">
			<div class="form-group">
				<label for="entrar-email">E-mail:</label>
				<div class="input-group">
					<div class="input-group-addon">@</div>
	      		<input id="entrar-email" name="entrar-email" class="form-control" type="email" placeholder="Seu e-mail" required="required">
      			</div>
			</div>
			
			<div class="form-group">
				<label for="entrar-senha">Senha:</label>
				<input id="entrar-senha" name="entrar-senha" class="form-control" type="password" placeholder="**********" required="required">
			</div>

			<button type="submit" class="btn btn-primary">Entrar</button>

		</form>
	</div>


	<div class="col-md-6">
		<h2>Cadastrar</h2>
		<h4>Sou novo usuário</h4>

		<form method="post" action="Cadastrar">
			<div class="form-group">
				<label for="cadastrar-nome">Nome:</label>
				<input id="cadastrar-nome" name="cadastrar-nome" class="form-control" type="text" placeholder="Seu nome" required="required">
			</div>

			<div class="form-group">
				<label for="cadastrar-email">E-mail:</label>
				<div class="input-group">
					<div class="input-group-addon">@</div>
	      			<input id="cadastrar-email" name="cadastrar-email" class="form-control" type="email" placeholder="Seu e-mail" required="required">
      			</div>
			</div>
			
			<div>
				<label for="cadastrar-cpf">CPF:</label>
				<input id="cadastrar-cpf" name="cadastrar-cpf" type="text" class="form-control" placeholder="000.000.000-00" maxlength="14" required="required">
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