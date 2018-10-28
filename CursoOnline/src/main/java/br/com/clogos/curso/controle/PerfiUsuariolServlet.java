package br.com.clogos.curso.controle;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.clogos.curso.dao.UsuarioDAO;
import br.com.clogos.curso.entidades.Usuario;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/PerfilUsuario")
public class PerfiUsuariolServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioDAO usuarioDAO;
       
    /**
     * @see HttpServlet#HttpServlet()	
     */
    public PerfiUsuariolServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuariologado");
		
		if(usuarioLogado != null) {
			request.setAttribute("listaCursoUsuario", usuarioDAO.obterListaCursosUsuario(usuarioLogado.getIdUsuario()));
			request.getRequestDispatcher("PerfilUsuario.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("Index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
