package com.rafael.pedidovenda.util.jsf;

import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

import javax.faces.application.FacesMessage;

import static javax.faces.context.FacesContext.getCurrentInstance;

public class FacesUtil {
	
	public static boolean isPostback() {
		return getCurrentInstance().isPostback();
	}
	
	public static boolean isNotPostback() {
		return !isPostback();
	}

	public static void addErrorMessage(String message) {
		getCurrentInstance().addMessage(null, 
				new FacesMessage(SEVERITY_ERROR, message, message));
	}
	
	public static void addInfoMessage(String message) {
		getCurrentInstance().addMessage(null, 
				new FacesMessage(SEVERITY_INFO, message, message));
	}
}
