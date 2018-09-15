package br.com.clogos.curso.controle;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.clogos.curso.dao.GenericDAO;
import br.com.clogos.curso.dao.impl.GenericDAOImpl;
import br.com.clogos.curso.entidades.Usuario;
import br.com.clogos.curso.seguranca.CriptografiaBase64;

/**
 * Servlet implementation class EntrarServlet
 */
@WebServlet (urlPatterns = "/Cadastrar")
public class CadastrarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private GenericDAO genericDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastrarServlet() {
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
		try {
			Usuario usuario = new Usuario();
			usuario.setNomeUsuario(request.getParameter("cadastrar-nome"));
			usuario.setEmailUsuario(request.getParameter("cadastrar-email"));
			usuario.setCpfUsuario(request.getParameter("cadastrar-cpf").replace(".", "").replace("-", ""));
			usuario.setTelefoneUsuario(request.getParameter("cadastrar-telefone").replace("(", "").replace(")", "").replace("-", ""));
			usuario.setSenhaUsuario(criptografarSenha(request.getParameter("cadastrar-senha")));
			usuario.setQdtCompartilhamento(BigDecimal.ZERO.intValue());
			
			if(getGenericDAO().save(usuario)) {
				HttpSession session = request.getSession();
				session.setAttribute("usuariologado", usuario);
				session.setMaxInactiveInterval(20*60);
				request.getRequestDispatcher("/Index.jsp").forward(request, response);
				
			} else {
				request.setAttribute("errorCadastro", "Problemas ao Cadastrar, contate a coordenação do Colégio Logos !!!");
			    request.getRequestDispatcher("/Entrar.jsp").forward(request, response);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}	
	}
	
	@SuppressWarnings("rawtypes")
	public GenericDAO getGenericDAO() {
		return genericDAO == null ? genericDAO = new GenericDAOImpl() : genericDAO;
	}
	
	private String criptografarSenha(String senha) {
		return CriptografiaBase64.encrypt(senha);
	}
}
