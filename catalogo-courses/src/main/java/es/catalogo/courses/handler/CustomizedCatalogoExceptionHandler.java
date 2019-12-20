package es.catalogo.courses.handler;

import java.util.Iterator;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import es.catalogo.courses.exception.NoContentException;

@ControllerAdvice
public class CustomizedCatalogoExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			  													  HttpHeaders headers, HttpStatus status, 
			  													  WebRequest request) {
		String errorMessage = null;
		FieldError fieldError = null;
		
		Iterator<FieldError> it = ex.getBindingResult().getFieldErrors().iterator();
		
		while (it.hasNext()) {
			fieldError = it.next();
			errorMessage = fieldError.getDefaultMessage();
		}
		
		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(NoContentException.class)
    public ResponseEntity<String> handleException(NoContentException e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
    }

}
