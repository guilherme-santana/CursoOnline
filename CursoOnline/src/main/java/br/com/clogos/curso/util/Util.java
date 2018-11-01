package br.com.clogos.curso.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import br.com.clogos.curso.entidades.Usuario;

public class Util {

	public static Long getIdCursoAndamento(HttpServletRequest request) throws ServletException {
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuariologado");
		if (usuario != null) {
			return usuario.getCursoAndamento();
		} else {
			throw new ServletException("Erro ao recuperar Curso Andamento Session");
		}
	}
	
	public static String extrairMensagem(Exception e) {
		if(e.getCause() == null) {
			return e.getMessage();
		} else {
			return e.getCause().getMessage();
		}
	}
	
	/**
	 * Colocar a mascara no número do CPF
	 * @param cpf
	 * @return
	 */
	public static String formataCPF(String cpf) {
		StringBuilder sBuilder = new StringBuilder(cpf)
				.insert(3, ".").insert(7, ".").insert(11, "-");
		return sBuilder.toString();
	}
	
	/**
	 * Formatar data
	 * @param data
	 * @return
	 */
	public static String formatarData(Date data) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return dateFormat.format(data);
	}
	
	public static Date convertStringToDate(String param) {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date data = null;
		try {
			data = formato.parse(param);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * Valida número de CPF
	 * @param CPF
	 * @return
	 */
	public static boolean isCPF(String CPF) {
		// considera-se erro CPF's formados por uma sequencia de numeros iguais
		if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222")
				|| CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555")
				|| CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888")
				|| CPF.equals("99999999999") || (CPF.length() != 11))
			return (false);

		char dig10, dig11;
		int sm, i, r, num, peso;

		// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				// converte o i-esimo caractere do CPF em um numero:
				// por exemplo, transforma o caractere '0' no inteiro 0
				// (48 eh a posicao de '0' na tabela ASCII)
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else
				dig10 = (char) (r + 48); // converte no respectivo caractere numerico

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else
				dig11 = (char) (r + 48);

			// Verifica se os digitos calculados conferem com os digitos informados.
			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}
}
