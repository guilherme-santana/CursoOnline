package br.com.clogos.curso.controle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.clogos.curso.entidades.Usuario;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/Index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("Index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuariologado");
		Integer qdt = usuarioLogado.getQdtCompartilhamento();
		
		if(qdt != null && qdt < 2) {
			qdt += 1;
			usuarioLogado.setQdtCompartilhamento(qdt);
			request.setAttribute("usuariologado", usuarioLogado);
			request.getRequestDispatcher("/Index.jsp").forward(request, response);
		} else {
			System.out.println(request.getParameter("idcurso"));
			request.getRequestDispatcher("/Curso.jsp").forward(request, response);
		}
	}

}
