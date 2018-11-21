<div id="menu">
	<%@include file="template/menu.jsp"%>
</div>

<div class="container">
	
	<section id="introducao">
	     <div class="container">
	     	<div class="page-header"><h1>Introdução <small>${conteudoCurso.curso.nomeCurso}</small></h1></div>
	        <div class="text-justify container text-muted">
	        	<c:if test="${errorMessageConteudo != null}">
					<div class="alert alert-warning" role="alert">${errorMessageConteudo}</div>
				</c:if>
				<c:out value="${conteudoCurso.introducaoCurso}"/>
				<br/><br/>
				<p>Baixar Arquivo: 
				<a href="pdf/${conteudoCurso.nomeArquivo}" target="_blank"><img alt="..." src="img/download_pdf.jpg" width="60" height="40"></a></p>
	        </div>
	     </div>
	</section>
	
	<nav aria-label="...">
	  <ul class="pager">
	    <li class="next"><a href="CursoVideo?value=${conteudoCurso.linkVideo}&nomeCurso=${conteudoCurso.curso.nomeCurso}">Próximo <span aria-hidden="true">&rarr;</span></a></li>
	  </ul>
	</nav>
</div>
	

<div id="footer">
	<%@include file="template/footer.jsp"%>
</div>