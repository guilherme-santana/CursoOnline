package br.com.clogos.curso.controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.clogos.curso.dao.GenericDAO;
import br.com.clogos.curso.dao.impl.GenericDAOImpl;

/**
 * Servlet implementation class EntrarServlet
 */
@WebServlet (urlPatterns = "/Curso")
public class ConteudoCursoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private GenericDAO genericDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConteudoCursoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("Curso.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}	
	}
	
	@SuppressWarnings("rawtypes")
	public GenericDAO getGenericDAO() {
		return genericDAO == null ? genericDAO = new GenericDAOImpl() : genericDAO;
	}
}
