package br.com.clogos.curso.controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.clogos.curso.dao.GenericDAO;
import br.com.clogos.curso.dao.impl.GenericDAOImpl;
import br.com.clogos.curso.entidades.Exercicio;
import br.com.clogos.curso.entidades.RespostaExercicio;

/**
 * Servlet implementation class EntrarServlet
 */
@WebServlet (urlPatterns = "/CursoExercicio")
public class ExercicioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private GenericDAO genericDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExercicioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Exercicio> listaExercicio = (List<Exercicio>) getGenericDAO().findAll(Exercicio.class, "idExercicio", "", "");
		
		for(Exercicio exercicio : listaExercicio) {
			List<RespostaExercicio> listaRespostas = (List<RespostaExercicio>) getGenericDAO().findIDList(RespostaExercicio.class, "fkExercicio", exercicio.getIdExercicio());
			exercicio.setListaResposta(listaRespostas);
		}
		
		request.setAttribute("listaExercicio", listaExercicio);
		
		request.getRequestDispatcher("CursoExercicio.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			System.out.println(request.getParameter("resposta1"));
			
			
			request.getRequestDispatcher("CursoConcluido.jsp").forward(request, response);	
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}	
	}
	
	@SuppressWarnings("rawtypes")
	public GenericDAO getGenericDAO() {
		return genericDAO == null ? genericDAO = new GenericDAOImpl() : genericDAO;
	}
}
