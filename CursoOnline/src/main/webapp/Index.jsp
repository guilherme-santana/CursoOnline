<script type="text/javascript">
$(function() {

	$('#tag-form-submit').on('click', function(e) {
	    e.preventDefault();
	    $.ajax({
	        type: "POST",
	        url: "Index",
	        //data: $('form.tagForm').serialize(),
	        success: function(response) {
	            alert(response['response']);
	        },
	        error: function() {
	            alert('Error');
	        }
	    });
	    return false;
	});
	});
</script>
	
	<div id="menu">
		<%@include file="template/menu.jsp"%>	
	</div>

	<section id="cursos" class="container">
		<h2>Cursos disponíveis</h2>
		
		<div class="row">
			<c:forEach items="${requestScope.listaCursos}" var="cursos">
				<div class="col-sm-6 col-md-4 col-lg-3">
					<figure class="thumbnail">
						<img src="img/enfermagem.jpg" alt="">
						<figcaption class="caption">
							<h3><c:out value="${cursos.nomeCurso}"></c:out></h3>
							<p><c:out value="${cursos.descricaoCurso}"></c:out></p>
							
							<c:if test="${empty sessionScope.usuariologado}">
								<p><a href="Entrar.jsp" class="btn btn-primary" role="button">Habilitar Curso</a></p>
							</c:if>
							
							<c:if test="${not empty sessionScope.usuariologado}">
								<p><a href="Compartilhar?id=${cursos.idCurso}" class="btn btn-success" role="button">Iniciar</a></p>
								<!-- <button class="btn btn-success" data-toggle="modal" data-target="#modal-mensagem">Iniciar</button>-->
							</c:if>
							
							
						</figcaption>
					</figure>
				</div>
			</c:forEach>
			
			<!-- 			
			<div class="col-sm-6 col-md-4 col-lg-3">
				<figure class="thumbnail">
					<img src="img/radiologia.jpg" alt="">
					<figcaption class="caption">
						<h3>Radiologia</h3>
						<p>A Radiologia é uma ciência cujo objetivo é a implantação do tratamento de doenças e o cuidado ao ser humano</p>
						<c:if test="${empty sessionScope.usuariologado}">
							<p><a href="Entrar.jsp" class="btn btn-primary" role="button">Habilitar Curso</a></p>
						</c:if>
						
						<c:if test="${not empty sessionScope.usuariologado}">
							<p><a href="Compartilhar.jsp" class="btn btn-success" role="button">Iniciar</a></p>
						</c:if>
					</figcaption>
				</figure>
			</div>
			
			<div class="col-sm-6 col-md-4 col-lg-3">
				<figure class="thumbnail">
					<img src="img/cuidador.jpg" alt="">
					<figcaption class="caption">
						<h3>Cuidador de Idosos</h3>
						<p>O Cuidador de Idosos é uma ciência cujo objetivo é a implantação do tratamento de doenças e o cuidado ao ser humano</p>
						<c:if test="${empty sessionScope.usuariologado}">
							<p><a href="Entrar.jsp" class="btn btn-primary" role="button">Habilitar Curso</a></p>
						</c:if>
						
						<c:if test="${not empty sessionScope.usuariologado}">
							<p><a href="Compartilhar.jsp" class="btn btn-success" role="button">Iniciar</a></p>
						</c:if>
					</figcaption>
				</figure>
			</div>
			-->
		</div>
	</section>
	
	<section id="modal">
		<div class="modal fade" id="modal-mensagem">
		    <div class="modal-dialog">
		         <div class="modal-content">
		             <div class="modal-header">
		                 <!-- <button type="button" class="close" data-dismiss="modal"><span>×</span></button>-->
		                 <h4 class="modal-title">Escolha como quer Compartilhar</h4>
		              	 <h3>Foi Compartilhado ${sessionScope.usuariologado.qdtCompartilhamento} de 3!</h3>
		             </div>
		             <div class="modal-body">
			             <div class="row">
							 <div class="col-sm-6 col-md-4">
								 <div class="thumbnail">
								 	<img src="img/facebook.png" height="60" width="90" alt="..."/><br/>
								 	<div align="center">
								 		<div class="fb-share-button" data-href="http://www.clogos.com.br" data-layout="button" data-size="large" 
								 		data-mobile-iframe="true"></div>
								 	</div>
								 </div>
							 </div>
							 
							 <div class="col-sm-6 col-md-4">
								 <div class="thumbnail">
								 	<img src="img/twitter.png" height="60" width="90" alt="..."/><br/>
									<div align="center">
										<a class="twitter-share-button" href="https://twitter.com/share" 
										data-size="large" data-url="http://www.clogos.com.br" data-text="Curso Online - Colégio Logos">Tweet</a>										
	  									<script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>
									</div>									
								 </div>
							 </div>
							 
							 <div class="col-sm-6 col-md-4">
								 <div class="thumbnail">
								 	<img src="img/gplus.png" height="60" width="90" alt="..."/><br/>
									<div align="center">
										<script src="https://apis.google.com/js/platform.js" async defer>{lang: 'pt-BR'}</script>
										<div class="g-plus" data-action="share" data-annotation="bubble" data-width=100 data-height=30 
										data-href="http://www.clogos.com.br"></div>
									</div>									
								 </div>
							 </div>
						 </div>

		             </div>
		             
		             <form class="tagForm" id="tag-form" method="post" action="Index">
			             <div class="modal-footer">
			             	<input id="tag-form-submit" type="submit" class="btn btn-primary" value="Validar">
			             </div>
		             </form>
		         </div>
		     </div>
		 </div>
	</section>
	
	<div id="footer">
		<%@include file="template/footer.jsp"%>	
	</div>