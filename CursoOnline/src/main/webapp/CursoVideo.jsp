<div id="menu">
	<%@include file="template/menu.jsp"%>
</div>

<div class="container">
	
	<section id="video">
	     <div class="container">
	     	<div class="page-header"><h1>Vídeo Aula <small> <c:out value="${nomeCurso}"/></small></h1></div>
	        <div class="embed-responsive embed-responsive-16by9">
				<iframe width="560" height="315" src=${linkVideo} 
				frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
	    	</div>
	     </div>
	</section>
	
	<nav aria-label="...">
	  <ul class="pager">
	    <li class="previous"><a href="Curso"><span aria-hidden="true">&larr;</span> Anterior</a></li>
	    <li class="next"><a href="CursoExercicio">Próximo <span aria-hidden="true">&rarr;</span></a></li>
	  </ul>
	</nav>
</div>
	

<div id="footer">
	<%@include file="template/footer.jsp"%>
</div>