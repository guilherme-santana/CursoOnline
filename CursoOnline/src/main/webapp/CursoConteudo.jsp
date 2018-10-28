<div id="menu">
	<%@include file="template/menu.jsp"%>
</div>

<div class="container">
	
	<section id="introducao">
	     <div class="container">
	     	<div class="page-header"><h1>Introdução <small>Enfermagem na prática</small></h1></div>
	        <div class="text-justify container text-muted">
	        	<c:if test="${errorMessageConteudo != null}">
					<div class="alert alert-warning" role="alert">${errorMessageConteudo}</div>
				</c:if>
				<c:out value="${conteudoCurso.introducaoCurso}"/>	
	        </div>
	     </div>
	</section>
	
	<nav aria-label="...">
	  <ul class="pager">
	    <li class="next"><a href="CursoVideo?value=${conteudoCurso.linkVideo}">Próximo <span aria-hidden="true">&rarr;</span></a></li>
	  </ul>
	</nav>
</div>
	

<div id="footer">
	<%@include file="template/footer.jsp"%>
</div>