<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Curso Online - Colégio Logos</title>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.min.css">
	<link rel="stylesheet" href="css/estilos.css">
	<meta name="viewport" content="width=devide-width, initial-scale=1">
	
	<script type="text/javascript" src="js/myJavaScript.js"></script>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/jquery.maskedinput.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	

<script type="text/javascript">
    //Mascara no campos
    $(function() {
        $.mask.definitions['~'] = "[+-]";
        $("#date").mask("99/99/9999",{completed:function(){alert("completed!");}});
        $("#cadastrar-telefone").mask("(99)99999-9999");
        $("#phoneExt").mask("(999) 999-9999? x99999");
        $("#iphone").mask("+33 999 999 999");
        $("#tin").mask("99-9999999");
        $("#cadastrar-cpf").mask("999.999.999-99");
        $("#product").mask("a*-999-a999", { placeholder: " " });
        $("#eyescript").mask("~9.99 ~9.99 999");
        $("#po").mask("PO: aaa-999-***");
		$("#pct").mask("99%");

        $("input").blur(function() {
            $("#info").html("Unmasked value: " + $(this).mask());
        }).dblclick(function() {
            $(this).unmask();
        });
    });
    
    //Facebook
    (function(d, s, id) {
	  var js, fjs = d.getElementsByTagName(s)[0];
	  if (d.getElementById(id)) return;
	  js = d.createElement(s); js.id = id;
	  js.src = 'https://connect.facebook.net/pt_BR/sdk.js#xfbml=1&version=v3.1';
	  fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));
	
</script>

</head>

<body>

<header>
		<nav class="navbar navbar-default">
			<div class="container">

				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#collapse-navbar" aria-expanded="false">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>

					<a class="navbar-brand" href="Index">Cursos Online Logos</a>
				</div>

				<div class="collapse navbar-collapse" id="collapse-navbar">
					<ul class="nav navbar-nav navbar-right">
						
						
						<c:if test="${empty sessionScope.usuariologado}">
							<li><a href="Entrar.jsp">Entrar</a></li>
						</c:if>
						
						<c:if test="${not empty sessionScope.usuariologado}">
							<li><a href="#">${sessionScope.usuariologado.emailUsuario}</a></li>
							<li><a href="Logout">Sair</a></li>
						</c:if>
					</ul>
				</div>
			</div>
		</nav>
	</header>