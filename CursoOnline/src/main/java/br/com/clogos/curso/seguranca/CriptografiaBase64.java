package br.com.clogos.curso.seguranca;

import org.apache.commons.codec.binary.Base64;

public class CriptografiaBase64 {
	public static final String encrypt(final String text) {
		return new String(Base64.encodeBase64(text.getBytes()));
	}

	public static final String decrypt(final String text) {
		return new String(Base64.decodeBase64(text.getBytes()));
	}
}
