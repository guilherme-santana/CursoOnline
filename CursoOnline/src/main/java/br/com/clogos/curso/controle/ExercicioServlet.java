package br.com.clogos.curso.controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.clogos.curso.dao.ExercicioDAO;
import br.com.clogos.curso.dao.GenericDAO;
import br.com.clogos.curso.dao.impl.ExercicioDAOImpl;
import br.com.clogos.curso.dao.impl.GenericDAOImpl;
import br.com.clogos.curso.entidades.Exercicio;
import br.com.clogos.curso.entidades.RespostaExercicio;
import br.com.clogos.curso.entidades.Usuario;
import br.com.clogos.curso.util.RecuperarSessao;

/**
 * Servlet implementation class EntrarServlet
 */
@WebServlet (urlPatterns = "/CursoExercicio")
public class ExercicioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private GenericDAO genericDAO;
	private ExercicioDAO exercicioDAO;
       
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Enumeration<String> enumeration =  request.getParameterNames();
		
		if(!enumeration.hasMoreElements()) {
			request.setAttribute("listaExercicio", listarExercicioCursoandamento(request));
			request.getRequestDispatcher("CursoExercicio.jsp").forward(request, response);
		} else {
			doPost(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			Enumeration<String> enumeration =  request.getParameterNames();
			List<Exercicio> listaExercicioRespondido = new ArrayList<Exercicio>();
			
			while (enumeration.hasMoreElements()) {
				String obj = enumeration.nextElement();
				Exercicio exercicio = (Exercicio) getGenericDAO().findID(Exercicio.class, "idExercicio", Long.valueOf(obj));
				List<RespostaExercicio> listaResposta = (List<RespostaExercicio>) getGenericDAO().findIDList(RespostaExercicio.class, "fkExercicio", Long.valueOf(obj));
				exercicio.setListaResposta(setarOpcaoMarcada(listaResposta, Long.valueOf(request.getParameter(obj))));
				listaExercicioRespondido.add(exercicio);
			}
			
			request.setAttribute("listaRespondida", listaExercicioRespondido);
			request.getRequestDispatcher("CursoConcluido.jsp").forward(request, response);	
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}	
	}
	
	private Boolean validarResposta(Long idExercicio, Long idResposta) {
		return getExercicioDAO().respostaCorreta(idExercicio, idResposta);
	}
	
	private ExercicioDAO getExercicioDAO() {
		return exercicioDAO == null ? exercicioDAO = new ExercicioDAOImpl() : exercicioDAO;
	}
	
	@SuppressWarnings("unchecked")
	private List<Exercicio> listarExercicioCursoandamento(HttpServletRequest request) throws ServletException {
		List<Exercicio> listaExercicio = (List<Exercicio>) getGenericDAO().findIDList(Exercicio.class, "fkCurso", RecuperarSessao.getIdCursoAndamento(request));
		for(Exercicio exercicio : listaExercicio) {
			List<RespostaExercicio> listaRespostas = (List<RespostaExercicio>) getGenericDAO().findIDList(RespostaExercicio.class, "fkExercicio", exercicio.getIdExercicio());
			exercicio.setListaResposta(listaRespostas);
		}
		return listaExercicio;
	}
	
	private List<RespostaExercicio> setarOpcaoMarcada(List<RespostaExercicio> lista, Long opcaoMarcada) {
		List<RespostaExercicio> listaRet = new ArrayList<RespostaExercicio>();
		
		for(RespostaExercicio resp : lista) {
			resp.setOpcaoMarcada(opcaoMarcada);
			listaRet.add(resp);
		}
		return listaRet;
	}
	
	@SuppressWarnings("rawtypes")
	public GenericDAO getGenericDAO() {
		return genericDAO == null ? genericDAO = new GenericDAOImpl() : genericDAO;
	}
}
