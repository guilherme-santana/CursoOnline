package br.com.clogos.curso.controle;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.clogos.curso.dao.CursoDAO;
import br.com.clogos.curso.dao.GenericDAO;
import br.com.clogos.curso.dao.UsuarioDAO;
import br.com.clogos.curso.dao.impl.GenericDAOImpl;
import br.com.clogos.curso.entidades.Curso;
import br.com.clogos.curso.entidades.Exercicio;
import br.com.clogos.curso.entidades.Parametro;
import br.com.clogos.curso.entidades.RespostaExercicio;
import br.com.clogos.curso.entidades.Usuario;
import br.com.clogos.curso.entidades.UsuarioCurso;
import br.com.clogos.curso.entidades.UsuarioCursoPK;
import br.com.clogos.curso.util.Util;

/**
 * Servlet implementation class EntrarServlet
 */
@WebServlet (urlPatterns = "/CursoExercicio")
public class ExercicioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private GenericDAO genericDAO;
	
	@Inject
	private UsuarioDAO usuarioDAO;
	@Inject
	private CursoDAO cursoDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExercicioServlet() {
        super();
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuariologado");
		Enumeration<String> enumeration =  request.getParameterNames();
		Curso curso = cursoDAO.obterCurso(usuarioLogado.getCursoAndamento());
		
		if(!enumeration.hasMoreElements()) {
			request.setAttribute("nomeCurso", curso != null ? curso.getNomeCurso() : "");
			request.setAttribute("listaExercicio", listarExercicioCursoAndamento(request));
			request.getRequestDispatcher("CursoExercicio.jsp").forward(request, response);
		} else {
			doPost(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * A Opção Marcada é o idResposta
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuariologado");
			Enumeration<String> enumeration =  request.getParameterNames();
			List<Exercicio> listaExercicioRespondido = new ArrayList<Exercicio>();
			Integer qtdRespostaCorreta = BigDecimal.ZERO.intValue();
			
			while (enumeration.hasMoreElements()) {
				String obj = enumeration.nextElement();
				Exercicio exercicio = (Exercicio) getGenericDAO().findID(Exercicio.class, "idExercicio", Long.valueOf(obj));
				List<RespostaExercicio> listaResposta = (List<RespostaExercicio>) getGenericDAO().findIDList(RespostaExercicio.class, "fkExercicio", Long.valueOf(obj));
				exercicio.setListaResposta(setarOpcaoMarcada(listaResposta, Long.valueOf(request.getParameter(obj))));
				qtdRespostaCorreta += corrigirExercicio(listaResposta);
				listaExercicioRespondido.add(exercicio);
			}
			
			Integer mediaAcerto = obterMediaAcertos(listaExercicioRespondido.size(), qtdRespostaCorreta);
			
			request.setAttribute("nomeCurso", obterNomeCurso(usuarioLogado));
			request.setAttribute("mediaAcerto", mediaAcerto);
			request.setAttribute("habilitado", verificarSeHabilitadoECncluirCurso(mediaAcerto, usuarioLogado));
			request.setAttribute("listaRespondida", listaExercicioRespondido);
			request.getRequestDispatcher("CursoConcluido.jsp").forward(request, response);	
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}	
	}
	
	@SuppressWarnings("unchecked")
	private List<Exercicio> listarExercicioCursoAndamento(HttpServletRequest request) throws ServletException {
		List<Exercicio> listaExercicio = (List<Exercicio>) getGenericDAO().findIDList(Exercicio.class, "fkCurso", Util.getIdCursoAndamento(request));
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
	
	private Integer corrigirExercicio(List<RespostaExercicio> listaResp) {
		Integer retornaUmSeCorreto = BigDecimal.ZERO.intValue();
		
		for(RespostaExercicio item : listaResp) {
			if ((item.getOpcaoMarcada().equals(item.getIdResposta())) && item.getBolCorreta()) {
				retornaUmSeCorreto = BigDecimal.ONE.intValue();
			}
		}
		return retornaUmSeCorreto;
	}
	
	private Integer obterMediaAcertos(Integer qtdExercicio, Integer qtdAcertos) {
		Integer resultado = (int) ((Double.valueOf(qtdAcertos) / Double.valueOf(qtdExercicio)) * 100);
		return resultado;
	}
	
	private Boolean verificarSeHabilitadoECncluirCurso(Integer mediaAcerto, Usuario usuario) {
		Parametro parametro = (Parametro) getGenericDAO().findID(Parametro.class, "idParametro", BigDecimal.ONE.longValue());
		Boolean isHabilitado = mediaAcerto >= Long.valueOf(parametro.getValorParametro());
		
		if(isHabilitado) {
			UsuarioCurso usuarioCurso = new UsuarioCurso();
			usuarioCurso.setPk(new UsuarioCursoPK());
			usuarioCurso.getPk().setIdUsuario(usuario.getIdUsuario());
			usuarioCurso.getPk().setIdCurso(usuario.getCursoAndamento());
			usuarioCurso.setCodigoCertificado(gerarCodigoCertificado());
			usuarioCurso.setDataConclusaoCurso(new Date());
			usuarioDAO.updateUsuarioCursoConclusao(usuarioCurso);
		}
		
		return isHabilitado;
	}
	
	private String gerarCodigoCertificado() {
		Calendar calendar = Calendar.getInstance();
		
		Integer ano = calendar.get(Calendar.YEAR);
		Integer mes = calendar.get(Calendar.MONTH)+1;
		Integer dia = calendar.get(Calendar.DAY_OF_MONTH);
		Long time = calendar.getTimeInMillis();
		
		String concatenar = ano+""+mes+""+dia+"-"+time;
		
		return  concatenar;
	}
	
	private String obterNomeCurso(Usuario user) {
		String nomeCurso = "";
		if(user != null && user.getCursoAndamento() != null) {
			nomeCurso = cursoDAO.obterCurso(user.getCursoAndamento()).getNomeCurso();
		}
		
		return nomeCurso;
	}
	
	@SuppressWarnings("rawtypes")
	public GenericDAO getGenericDAO() {
		return genericDAO == null ? genericDAO = new GenericDAOImpl() : genericDAO;
	}
}
