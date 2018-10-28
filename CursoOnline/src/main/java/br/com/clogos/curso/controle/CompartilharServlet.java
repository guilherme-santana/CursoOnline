package br.com.clogos.curso.controle;

import java.io.IOException;
import java.util.Date;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.clogos.curso.controle.exception.CursoOnlineExceptionNegocial;
import br.com.clogos.curso.dao.CursoDAO;
import br.com.clogos.curso.dao.GenericDAO;
import br.com.clogos.curso.dao.impl.GenericDAOImpl;
import br.com.clogos.curso.entidades.Usuario;
import br.com.clogos.curso.entidades.UsuarioCurso;
import br.com.clogos.curso.entidades.UsuarioCursoPK;
import br.com.clogos.curso.util.Util;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/Compartilhar")
public class CompartilharServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CursoDAO cursoDAO;
	private GenericDAO genericDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompartilharServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idCurso = request.getParameter("id");
		
		if(idCurso != null) {
			Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuariologado");
			usuarioLogado.setCursoAndamento(Long.valueOf(idCurso));
			request.setAttribute("usuariologado", usuarioLogado);
			request.getRequestDispatcher("Compartilhar.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("Index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuariologado");
		Integer qdt = usuarioLogado.getQdtCompartilhamento();
		
		try {
			if(qdt != null && qdt < 2) {
				qdt += 1;
				usuarioLogado.setQdtCompartilhamento(qdt);
				request.setAttribute("usuariologado", usuarioLogado);
				request.getRequestDispatcher("/Compartilhar.jsp").forward(request, response);
			} else {
				request.setAttribute("conteudoCurso", cursoDAO.obterConteudoCurso(usuarioLogado.getCursoAndamento()));
				saveCursoAndamento(usuarioLogado);
				request.getRequestDispatcher("/CursoConteudo.jsp").forward(request, response);
			}
		} catch (PersistenceException e) {
			request.setAttribute("errorMessageConteudo", "ERRO: "+Util.extrairMensagem(e));
			request.getRequestDispatcher("/Compartilhar.jsp").forward(request, response);
		} catch (CursoOnlineExceptionNegocial e) {
			request.setAttribute("alertaMessageConteudo", "ALERTA: "+Util.extrairMensagem(e));
			request.getRequestDispatcher("/Compartilhar.jsp").forward(request, response);
		}
	}
	
	private void saveCursoAndamento(Usuario usuario) throws CursoOnlineExceptionNegocial {
		if(usuario != null) {
			//Verifica se existe outro curso sem concluir, assim não permite iniciar outro antes de concluir-lo.
			if(cursoDAO.existeCursoAndamentoNaoFinalizado(usuario)) {
				throw new CursoOnlineExceptionNegocial("Existe Cursos não concluídos, por favor finaliza-los antes de iniciar outro!");
			}
			
			//Verifica se o curso selecionado já foi concluído, caso sim será informado para escolher outro
			if(cursoDAO.verificarCursoAndamentoEstaFinalizado(usuario)) {
				throw new CursoOnlineExceptionNegocial("O Curso selecionado já foi concluído, por favor selecione outro curso!");
			}
			
			//Verificar se o curso de andamento é o mesmo em execução, caso não será feito a inclusão do mesmo
			if(!cursoDAO.existeCursoAndamento(usuario)) {
				UsuarioCurso usuarioCurso = new UsuarioCurso();
				UsuarioCursoPK pk = new UsuarioCursoPK();
				
				pk.setIdUsuario(usuario.getIdUsuario());
				pk.setIdCurso(usuario.getCursoAndamento());
				usuarioCurso.setPk(pk);
				usuarioCurso.setDataInicioCurso(new Date());
				usuarioCurso.setCursoConcluido(Boolean.FALSE);
				getGenericDAO().save(usuarioCurso);				
			}
		} 
	}
	
	@SuppressWarnings("rawtypes")
	private GenericDAO getGenericDAO() {
		return genericDAO == null ? genericDAO = new GenericDAOImpl() : genericDAO;
	}

}
