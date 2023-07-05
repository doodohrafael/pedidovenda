package com.rafael.pedidovenda.util.report;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.jdbc.Work;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

/*
* Responsável por executar e exportar o relatório para o formato PDF.
*/

@SuppressWarnings({ "deprecation", "rawtypes" })
public class ExecutorRelatorio implements Work {
	
	private String caminhoRelatorio;
	private HttpServletResponse response;
	private Map<String, Object> parametros;
	private String nomeArquivoSaida;
	
	private boolean relatorioGerado;
	
	public ExecutorRelatorio(String caminhoRelatorio, HttpServletResponse response, Map<String, Object> parametros, 
			String nomeArquivoSaida) {
		this.caminhoRelatorio = caminhoRelatorio;
		this.response = response;
		this.parametros = parametros;
		this.nomeArquivoSaida = nomeArquivoSaida;
		
		this.parametros.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
	}

	@Override
	public void execute(Connection connection) throws SQLException {
		try {
			InputStream relatorioStream = this.getClass().getResourceAsStream(caminhoRelatorio);
			
			JasperPrint print = JasperFillManager.fillReport(relatorioStream, parametros, connection);
			relatorioGerado = !print.getPages().isEmpty();
			
			if (relatorioGerado) {
				JRExporter exportador = new JRPdfExporter();
				exportador.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
				exportador.setParameter(JRExporterParameter.JASPER_PRINT, print);
				
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "attachment; filename=\"" 
						+ nomeArquivoSaida + "\"");
				
				exportador.exportReport();
			}
		} catch (Exception e) {
			throw new SQLException("Erro ao executar relatório " + caminhoRelatorio, e);
		}
	}

	public boolean isRelatorioGerado() {
		return relatorioGerado;
	}
}
