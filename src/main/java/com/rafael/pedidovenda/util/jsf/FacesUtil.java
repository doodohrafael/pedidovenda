package com.rafael.pedidovenda.util.jsf;

import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

import javax.faces.application.FacesMessage;
import static javax.faces.context.FacesContext.getCurrentInstance;;

public class FacesUtil {

	public static void addErrorMessage(String message) {
		getCurrentInstance().addMessage(null, 
				new FacesMessage(SEVERITY_ERROR, message, message));
	}
}
