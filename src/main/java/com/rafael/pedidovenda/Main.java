package com.rafael.pedidovenda;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;

public class Main {
	
	public void enviar() throws IOException {
		Properties props = new Properties();
		props.load(getClass().getResourceAsStream("/mail.properties"));

		SimpleEmail email = new SimpleEmail();
		email.setHostName(props.getProperty("mail.server.host"));
		email.setSmtpPort(Integer.parseInt(props.getProperty("mail.server.port")));
		email.setAuthenticator(
				new DefaultAuthenticator(props.getProperty("mail.username"), props.getProperty("mail.password")));
		email.setSSLOnConnect(Boolean.parseBoolean(props.getProperty("mail.enable.ssl")));
		
		try {
			email.setFrom(props.getProperty("mail.username"));
			email.setSubject("Subject");
			email.setMsg(props.getProperty("mail.password"));
			email.addTo("doodohrafael@gmail.com");
			
			email.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		Main main = new Main();
		main.enviar();
	}

}
