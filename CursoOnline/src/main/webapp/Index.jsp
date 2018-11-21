<div id="menu">
	<%@include file="template/menu.jsp"%>	
</div>

<section id="cursos" class="container">
	<h2>Cursos disponíveis</h2>
	
	<div class="row">
		<c:forEach items="${requestScope.listaCursos}" var="cursos">
			<div class="col-sm-6 col-md-4 col-lg-3">
				<figure class="thumbnail">
					<img src="img/curso/${cursos.nomeImagemCurso}" alt="">
					<figcaption class="caption">
						<h3><c:out value="${cursos.nomeCurso}"></c:out></h3>
						<p><c:out value="${cursos.descricaoCurso}"></c:out></p>
						
						<c:if test="${empty sessionScope.usuariologado}">
							<p><a href="Entrar.jsp" class="btn btn-primary" role="button">Habilitar Curso</a></p>
						</c:if>
						
						<c:if test="${not empty sessionScope.usuariologado}">
							<p><a href="Compartilhar?id=${cursos.idCurso}" class="btn btn-success" role="button">Iniciar</a></p>
						</c:if>
					</figcaption>
				</figure>
			</div>
		</c:forEach>
		
		
	</div>
</section>
<div id="footer">
	<%@include file="template/footer.jsp"%>	
</div>