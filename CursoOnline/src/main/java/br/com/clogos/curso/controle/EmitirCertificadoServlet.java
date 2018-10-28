package br.com.clogos.curso.controle;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.clogos.curso.dao.UsuarioDAO;
import br.com.clogos.curso.entidades.Usuario;
import br.com.clogos.curso.util.Util;
import br.com.clogos.curso.vo.UsuarioCertificadoVO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 * Servlet implementation class EmitirCertificadoServlet
 */
@WebServlet("/EmitirCertificadoServlet")
public class EmitirCertificadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioDAO usuarioDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmitirCertificadoServlet() {
        super();
       }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuariologado");
		
		//Quando for chamado pela página de Perfil
		if(usuarioLogado.getCursoAndamento() == null) {
			usuarioLogado.setCursoAndamento(Long.valueOf(request.getParameter("curso-perfil")));
		}
		
		try {
			if(usuarioLogado != null) {
				UsuarioCertificadoVO certificadoVO = usuarioDAO.obterDadosEmitirCertificado(usuarioLogado.getIdUsuario(), usuarioLogado.getCursoAndamento());
				gerarRelatorio(request.getServletContext(), response, request, certificadoVO);
			}
		} catch (PersistenceException e) {
			request.setAttribute("errorMessageConcluido", "ERRO: "+Util.extrairMensagem(e));
		}
	}
	
	private void gerarRelatorio(ServletContext context, HttpServletResponse response, HttpServletRequest request, UsuarioCertificadoVO vo) {
		
		Map<String, Object> paramentros = new HashMap<String, Object>();
		File fileJasper = new File(context.getRealPath("/relatorio/certificado.jasper"));
		File fileLogo = new File(context.getRealPath("/relatorio/logo.png"));
		File fileModura = new File(context.getRealPath("/relatorio/moldura_2.png"));
	
		
	    try {
	    	BufferedImage logo = ImageIO.read(fileLogo);
	    	BufferedImage modura = ImageIO.read(fileModura);
	    	paramentros.put("LOGO", logo);
	    	paramentros.put("MODURA", modura);
			paramentros.put("CPFALUNO", Util.formataCPF(vo.getCpfUsuario()));
			paramentros.put("NOMEALUNO", vo.getNomeUsuario());
			paramentros.put("DADOSCURSO", formaStringDadosCurso(vo));
			paramentros.put("CODCERTIFICADO", vo.getCodigoCertificado());
	    	
			byte[] bytes = JasperRunManager.runReportToPdf(fileJasper.getAbsolutePath(), paramentros);
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			ServletOutputStream ouputStream = response.getOutputStream();
			ouputStream.write(bytes, 0, bytes.length);
			ouputStream.flush();
			ouputStream.close();
		    
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		} 
	}
	
	private String formaStringDadosCurso(UsuarioCertificadoVO certificadoVO) {
		String concatena = null;
		
		if(certificadoVO != null) {
			concatena = certificadoVO.getNomecurso();
			concatena += retornaDataPorExtenso(certificadoVO.getDataConclusao());
			concatena += " com carga horária de "+certificadoVO.getCargaHoraria()+" horas.";
		}
		
		return concatena;
	}
	
	public static String retornaDataPorExtenso(Date date) {
		String data = "";
		Calendar calendario = Calendar.getInstance();
        Locale localizacao = new Locale("pt","BR");
        calendario.setTime(date);

        data += " em " + Integer.toString(calendario.get(Calendar.DAY_OF_MONTH));
        data += " " + calendario.getDisplayName(Calendar.MONTH, Calendar.LONG, localizacao).toLowerCase();
        data += " de " + Integer.toString(calendario.get(Calendar.YEAR));
        return data;
	}


}
