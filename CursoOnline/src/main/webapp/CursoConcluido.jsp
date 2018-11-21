<div id="menu">
	<%@include file="../template/menu.jsp"%>
</div>

<section id="exercicio">
     <div class="container">
        <div class="page-header"><h1>Resultado <small><c:out value="${nomeCurso}"/></small></h1></div>
        
       	<c:if test="${habilitado}">
       		<div class="panel panel-success">
	       		<div class="panel-heading text-justify">
	       			Você obteve a média de ${mediaAcerto}% e está habilitado.<br/>
	       		</div>
	        	<div class="panel-body text-muted">
	        		Verifique suas resposta e emita seu Certificado.
	        	</div>
        	</div>
       	</c:if>
       	
       	<c:if test="${not habilitado}">
       		<div class="panel panel-danger">
	       		<div class="panel-heading text-justify">
	       			Você obteve a média de ${mediaAcerto}% e não está habilitado.<br/>
	       		</div>
	        	
	        	<div class="panel-body text-muted">
	        		Verifique suas resposta e por favor Tente Novamente.
	        	</div>
        	</div>
       	</c:if>
        

       	<c:if test="${errorMessageConcluido != null}">
			<div class="alert alert-warning" role="alert">${errorMessageConcluido}</div>
		</c:if>
       	
       	<div class="panel panel-default">
       	<c:forEach items="${requestScope.listaRespondida}" var="listaRespondida">
       		<div class="panel-heading text-justify"><c:out value="${listaRespondida.questaoExercicio}"></c:out></div>
       		
       		<div class="panel-body text-justify text-muted">
       			<c:forEach items="${listaRespondida.listaResposta}" var="listaResp">
				<div class="radio">
				      	<c:if test="${listaResp.idResposta eq listaResp.opcaoMarcada}">
				      		<c:if test="${listaResp.bolCorreta}">
				      			<label class="bg-success" style="font-weight: bold;">
					      			<input type="radio" name="${listaResp.exercicio.idExercicio}" value="${listaResp.idResposta}" checked disabled>
					    			<c:out value="${listaResp.opcaoResposta}"></c:out> (Correta)
					  			</label>
				      		</c:if>
				      		<c:if test="${not listaResp.bolCorreta}">
				      			<label class="bg-danger" style="font-weight: bold;">
					      			<input type="radio" name="${listaResp.exercicio.idExercicio}" value="${listaResp.idResposta}" checked disabled>
					    			<c:out value="${listaResp.opcaoResposta}"></c:out> (Incorreta)
					  			</label>
				      		</c:if> 
				    	</c:if> 
				    	
				    	<c:if test="${listaResp.idResposta ne listaResp.opcaoMarcada}">
				    		<label>
					    		<input type="radio" name="${listaResp.exercicio.idExercicio}" value="${listaResp.idResposta}" disabled>
					    		<c:out value="${listaResp.opcaoResposta}"></c:out>
				  			</label>		
				    	</c:if> 
				    
				</div>	
				</c:forEach>
        	</div>
        </c:forEach>
        </div>

        <div class="text-right">
        <c:if test="${habilitado}">
		    <form method="post" action="EmitirCertificadoServlet" target="_blank">
	       		<button type="submit" class="btn btn-success">Emitir Certificado</button>
	       	</form>    	
        </c:if>
        
        <c:if test="${not habilitado}">
	        <form method="get" action="Index" enctype=text/plain>
	        	<button type="submit" class="btn btn-warning">Fazer Novamente</button>
	        </form>
        </c:if>
        </div>
        
     </div>
</section>

<div id="footer">
	<%@include file="../template/footer.jsp"%>
</div>