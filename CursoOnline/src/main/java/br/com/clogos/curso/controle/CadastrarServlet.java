package br.com.clogos.curso.controle;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.clogos.curso.dao.GenericDAO;
import br.com.clogos.curso.dao.UsuarioDAO;
import br.com.clogos.curso.dao.impl.GenericDAOImpl;
import br.com.clogos.curso.entidades.Usuario;
import br.com.clogos.curso.seguranca.CriptografiaBase64;
import br.com.clogos.curso.servico.IndexServico;
import br.com.clogos.curso.util.Util;

/**
 * Servlet implementation class EntrarServlet
 */
@WebServlet (urlPatterns = "/Cadastrar")
public class CadastrarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioDAO usuarioDAO;
	private GenericDAO genericDAO;
	private String mensagem;
	
	@SuppressWarnings("rawtypes")
	public GenericDAO getGenericDAO() {
		return genericDAO == null ? genericDAO = new GenericDAOImpl() : genericDAO;
	}
       
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
			usuario.setTelefoneUsuario(request.getParameter("cadastrar-telefone"));
			usuario.setSenhaUsuario(criptografarSenha(request.getParameter("cadastrar-senha")));
			usuario.setQdtCompartilhamento(BigDecimal.ZERO.intValue());
			usuario.setDataCadastroUsuario(new Date());
			
			if(validarCPF(usuario.getCpfUsuario()) && cadastrarUsuario(usuario)) {
				usuario.setCpfUsuario(Util.formataCPF(usuario.getCpfUsuario()));
				usuario.setDataCadastroFormatada(Util.formatarData(usuario.getDataCadastroUsuario()));
				
				HttpSession session = request.getSession();
				session.setAttribute("usuariologado", usuario);
				session.setMaxInactiveInterval(20*60);
				request.setAttribute("listaCursos", new IndexServico().listarTodosCursos());
				request.getRequestDispatcher("Index").forward(request, response);
				
			} else {
				request.setAttribute("errorCadastro", this.mensagem);
			    request.getRequestDispatcher("/Entrar.jsp").forward(request, response);
			}
		} catch (PersistenceException e) {
			request.setAttribute("errorCadastro", "ERRO: "+e.getCause().getMessage());
			request.getRequestDispatcher("/Entrar.jsp").forward(request, response);
		}	
	}
	
	/**
	 * 
	 * @param senha
	 * @return
	 */
	private String criptografarSenha(String senha) {
		return CriptografiaBase64.encrypt(senha);
	}
	
	private Boolean cadastrarUsuario(Usuario usuario) throws PersistenceException {
		Boolean ret = Boolean.FALSE;
		
		if(usuario != null) {
			try {
				Usuario user = usuarioDAO.existeUsuarioCastrado(usuario.getCpfUsuario(), usuario.getEmailUsuario());
				
				if(user == null) {
					ret = getGenericDAO().save(usuario);
				}
				
				if(!ret) {
					this.mensagem = "CPF: "+Util.formataCPF(usuario.getCpfUsuario())+" ou Email "+ usuario.getEmailUsuario()+" já cadastrado !!!";
				}
				
			} catch (PersistenceException e) {
				throw new PersistenceException(e);
			}
		}
		
		return ret;
	}
	
	/**
	 * Usa a função para validar o número do CPF
	 * @param numCpf
	 * @return
	 */
	private Boolean validarCPF(String numCpf) {
		Boolean isCpf = Boolean.FALSE;
		
		if(numCpf != null) {
			isCpf = Util.isCPF(numCpf);
		}
		
		if (!isCpf) {
			this.mensagem = "Número do CPF Inválido.";
		}
		
		return isCpf;
	}
}
