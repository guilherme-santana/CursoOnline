<div id="menu">
	<%@include file="template/menu.jsp"%>
</div>

<div class="container">
	
	<section id="exercicio">
	     <div class="container">
	        <div class="page-header"><h1>Exercício <small>Enfermagem na prática</small></h1></div>
	        <form method="get" action="CursoExercicio" enctype=text/plain>
	        	<div class="panel panel-default">
	        	<c:forEach items="${requestScope.listaExercicio}" var="listaExer">
	        		<div class="panel-heading text-justify"><c:out value="${listaExer.questaoExercicio}"></c:out></div>
	        		
	        		<div class="panel-body text-justify text-muted">
	        			<c:forEach items="${listaExer.listaResposta}" var="listaResp">
						<div class="radio">
						  <label>
						    <input type="radio" name="${listaResp.exercicio.idExercicio}" value="${listaResp.idResposta}" required="required">
						    <c:out value="${listaResp.opcaoResposta}"></c:out>
						  </label>
						</div>	
						</c:forEach>
		        	</div>
		        </c:forEach>
		        </div>
		        <div class="text-right">
		        	<button type="submit" class="btn btn-success">Enviar</button>
		        </div>
	        </form>
	     </div>
	</section>

	<nav aria-label="...">
	  <ul class="pager">
	    <li class="previous disabled"><a href="#"><span aria-hidden="true">&larr;</span> Anterior</a></li>
	  </ul>
	</nav>
</div>
	

<div id="footer">
	<%@include file="template/footer.jsp"%>
</div>