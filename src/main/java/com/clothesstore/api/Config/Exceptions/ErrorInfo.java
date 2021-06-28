package com.clothesstore.api.Config.Exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class ErrorInfo {

	@NonNull
	private HttpStatus statusCode;
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
	private LocalDateTime fecha = LocalDateTime.now();
	@NonNull
	private String message;
	@NonNull
    private String uriRequested;

	public ErrorInfo(HttpStatus statusCode, String message/* , String uriRequested */) {
		this.message = message;
		this.statusCode = statusCode;
		// this.uriRequested = uriRequested;
	}
	public String getMessage() {
        return message;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public String getUriRequested() {
        return uriRequested;
    }
}
