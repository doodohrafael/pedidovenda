package com.rafael.pedidovenda.util.jsf;

import static javax.faces.context.FacesContext.getCurrentInstance;
import static com.rafael.pedidovenda.util.jsf.FacesUtil.addErrorMessage;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import org.apache.log4j.Logger;
import static org.apache.log4j.Logger.getLogger;

import com.rafael.pedidovenda.service.NegocioException;

import lombok.var;

@SuppressWarnings("deprecation")
public class JsfExceptionHandler extends ExceptionHandlerWrapper {
	
	private static Logger log = getLogger(JsfExceptionHandler.class);

	private ExceptionHandler wrapped;
	private Iterator<ExceptionQueuedEvent> events;
	private NegocioException negocioException;
	
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
			
			negocioException = getNegocioException(exception);
			
			verifyExceptions(exception);
		}
		getWrapped().handle();
	}

	private NegocioException getNegocioException(Throwable exception) {
		if (exception instanceof NegocioException) {
			return (NegocioException) exception;
		} else if (exception.getCause() != null) {
			return getNegocioException(exception.getCause());
		}
		return null;
	}

	private void verifyExceptions(Throwable exception) {
		if (exception instanceof ViewExpiredException) {
			redirectUserToSomePage("/");
		} else if (this.negocioException != null) {
			handleNegocioException();
		} else {
			log.error("Erro de sistema: " + exception.getMessage(), exception);
			redirectUserToSomePage("/Erro.xhtml");
		}
	}

	private void redirectUserToSomePage(String page) {
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
	
	private void handleNegocioException() {
		addErrorMessage(negocioException.getMessage());
		events.remove();
	}
	
}