
<div id="menu">
	<%@include file="template/menu.jsp"%>
</div>

<div class="container">
	<h4>Escolha como quer Compartilhar, deve compartilhar 3 vezes</h4>
	<h3>Foi Compartilhado ${sessionScope.usuariologado.qdtCompartilhamento} de 3!</h3>
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
		 
		 <div class="col-sm-6 col-md-4">
			 <div class="thumbnail">
			 	<img src="img/whatsapp.png" height="60" width="90" alt="..."/><br/>
				<div align="center">
					<p><a href="https://api.whatsapp.com/send?text=http://www.clogos.com.br" class="btn btn-success" role="button">Compartilhar</a></p>
				</div>									
			 </div>
		 </div>
		 
		 <div>
			<form method="post" action="Compartilhar">
		       <input type="submit" class="btn btn-warning btn-lg btn-block" value="Validar">
			</form>
		 </div>		
	 </div>
</div>

<div id="footer">
	<%@include file="template/footer.jsp"%>	
</div>