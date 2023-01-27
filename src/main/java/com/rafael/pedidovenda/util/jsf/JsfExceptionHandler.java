package com.rafael.pedidovenda.util.jsf;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import static javax.faces.context.FacesContext.getCurrentInstance;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import lombok.var;

@SuppressWarnings("deprecation")
public class JsfExceptionHandler extends ExceptionHandlerWrapper {

	private ExceptionHandler wrapped;
	private Iterator<ExceptionQueuedEvent> events;
	
	public JsfExceptionHandler(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}
	
	@Override
	public ExceptionHandler getWrapped() {
		return this.wrapped;
	}

	@Override
	public void handle() throws FacesException {
		events = getUnhandledExceptionQueuedEvents().iterator();
		
		while(events.hasNext()) {
			ExceptionQueuedEvent event = events.next();
			ExceptionQueuedEventContext context = 
					(ExceptionQueuedEventContext) event.getSource();
			Throwable exception = context.getException();
			
			isViewExpiredException(exception);
		}
		getWrapped().handle();
	}

	private void isViewExpiredException(Throwable exception) {
		if (exception instanceof ViewExpiredException) {
			redirectUserToHomePage("/");
		}
	}

	private void redirectUserToHomePage(String page) {
		try {
			var facesContext = getCurrentInstance();
			var externalContext = facesContext.getExternalContext();
			var contextPath = externalContext.getRequestContextPath();
			
			externalContext.redirect(contextPath + page);
			facesContext.responseComplete();
			events.remove();
		} catch (IOException e) {
			throw new FacesException(e);
		}
	}
	
}