<div id="menu">
	<%@include file="template/menu.jsp"%>
</div>

<div class="container">
	
	<c:if test="${errorMessageConteudo != null}">
		<div class="alert alert-danger" role="alert">${errorMessageConteudo}</div>
	</c:if>
	
	<c:if test="${alertaMessageConteudo != null}">
		<div class="alert alert-warning" role="alert">${alertaMessageConteudo}</div>
		<div>
			<form method="get" action="Index">
		       <input type="submit" class="btn btn-primary btn-lg btn-block" value="Selecionar Outro Curso">
			</form>
		 </div>	
	</c:if>
		
		
		
	<div class="row">
			 <div class="col-md-4">
				 <div class="thumbnail">
				 	<img src="img/usuario.png" height="60" width="90" alt="..."/><br/>
				 	<div class="text-center">
				 		<p>${sessionScope.usuariologado.nomeUsuario}</p>
				 		<p>${sessionScope.usuariologado.cpfUsuario}</p>
				 		<p>${sessionScope.usuariologado.emailUsuario}</p>
				 		<p>${sessionScope.usuariologado.telefoneUsuario}</p>
				 		<p>Data de Cadastro: ${sessionScope.usuariologado.dataCadastroFormatada}</p>	
				 	</div>
				 </div>
			 </div>
			 
			 <div class="col-md-8">
				 <div class="thumbnail">
				 	<table class="table table-hover">
				 		
				 		<tr>
						  <td><strong>Nome Curso</strong></td>
						  <td><strong>Carga Horária</strong></td>
						  <td><strong>Concluído</strong></td>
						  <td><strong>Certificado</strong></td>
						</tr>
				 		<c:forEach items="${requestScope.listaCursoUsuario}" var="cursosUsuario">
							<tr>
							  <td><c:out value="${cursosUsuario.nomeCurso}"></c:out></td>
							  <td><c:out value="${cursosUsuario.cargaHoraria}"></c:out></td>
							  <td><c:out value="${cursosUsuario.cursoConcluido}"></c:out></td>
							  <td>
								  <c:if test="${cursosUsuario.cursoConcluido eq 'Sim'}">
									<form method="post" action="EmitirCertificadoServlet" target="_blank">
										<input type="hidden" name="curso-perfil" value="${cursosUsuario.idCurso}"/>
										<button class="btn btn-default" type="submit">Emitir</button>
									</form>
								  </c:if>
								  <c:if test="${cursosUsuario.cursoConcluido eq 'Não'}">
									<button class="btn btn-default" disabled="disabled" type="submit">Emitir</button>
								  </c:if>
							  </td>
							</tr> 	
				 		</c:forEach>
				 	</table>	
				 </div>
			 </div>	
		</div>
	</div>

<div id="footer">
	<%@include file="template/footer.jsp"%>	
</div>