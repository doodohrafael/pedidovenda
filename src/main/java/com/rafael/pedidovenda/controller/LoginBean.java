package com.rafael.pedidovenda.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.rafael.pedidovenda.util.jsf.FacesUtil.addErrorMessage;

import lombok.Getter;
import lombok.Setter;

@Named
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PATH = "/j_spring_security_check";
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private HttpServletRequest request;
	
	@Inject
	private HttpServletResponse response;

	@Getter @Setter
	private String email;
	
	public void preRender() {
		if ("true".equals(request.getParameter("invalid"))) {
			addErrorMessage("Usuário ou senha inválidos!");
		}
	}
	
	public void login() throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(PATH);
		dispatcher.forward(request, response);
		
		facesContext.responseComplete();
	}
	
}
