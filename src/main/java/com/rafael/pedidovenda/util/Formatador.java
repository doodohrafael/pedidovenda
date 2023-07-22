package com.rafael.pedidovenda.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Formatador {
	
	private static final String PREFIXO_PAIS = "+55";
	
	private Formatador() {
		throw new IllegalStateException("Utility class");
	}
	
	public static String formatarTelefoneCliente(String telefone) {
		String telefoneSemMascara = telefone
				.replace("(", "")
				.replace(")", "")
				.replace(" ", "")
				.replace("-", "");
		return PREFIXO_PAIS + telefoneSemMascara;
	}
	
	public static String formatarData(Date data) {
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		return formatador.format(data);
	}
	
	public static String formatarDataEHora(Date dataEHora) {
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return formatador.format(dataEHora);
	}
	
	public static String formatarMoeda(BigDecimal valor) {
		Locale locale = new Locale.Builder().setLanguage("pt").setRegion("BR").build();
		NumberFormat formatador = NumberFormat.getCurrencyInstance(locale);
		return formatador.format(valor);
	}
	
	public static String formatarCpf(String cpf) {
		return cpf.replace(".", "")
				  .replace("-", "");
	}
	
	public static String formatarCnpj(String cnpj) {
		return cnpj.replace(".", "")
				   .replace("/", "")
				   .replace("-", "");
	}
	
}
