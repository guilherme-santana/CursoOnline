package br.com.clogos.curso.controle;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.clogos.curso.entidades.Usuario;
import br.com.clogos.curso.servico.ValidarUsuario;

/**
 * Servlet implementation class EntrarServlet
 */
@WebServlet (urlPatterns = "/EntrarLogin")
public class EntrarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ValidarUsuario validarUsuario = ValidarUsuario.getInstance(); 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EntrarServlet() {
        super();
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("Entrar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = new Usuario();
		usuario.setEmailUsuario(request.getParameter("entrar-email"));
		usuario.setSenhaUsuario(request.getParameter("entrar-senha"));
		usuario.setQdtCompartilhamento(BigDecimal.ZERO.intValue());
		
        
		if(validarUsuario.validarCredenciais(usuario)) {
			HttpSession session = request.getSession();
			session.setAttribute("usuariologado", usuario);
			session.setMaxInactiveInterval(20*60);
			System.out.println(session.getAttribute("usuariologado"));
			request.getRequestDispatcher("Index.jsp").forward(request, response);
			
        } else {
        	request.setAttribute("errorMessage", "Credencias está inválida !!!");
            request.getRequestDispatcher("/Entrar.jsp").forward(request, response);
        }
		
	}

}
