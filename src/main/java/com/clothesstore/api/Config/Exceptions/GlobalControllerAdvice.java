package com.clothesstore.api.Config.Exceptions;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ProductoNotFoundException.class)
	public ResponseEntity<ErrorInfo> handleProductoNoEncontrado(ProductoNotFoundException ex) {
		ErrorInfo apiError = new ErrorInfo(HttpStatus.NOT_FOUND, ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorInfo  apiError = new ErrorInfo(status, ex.getMessage());
		return ResponseEntity.status(status).headers(headers).body(apiError);
	}

	
	

}
