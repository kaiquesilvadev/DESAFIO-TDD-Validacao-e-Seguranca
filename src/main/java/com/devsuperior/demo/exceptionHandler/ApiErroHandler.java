package com.devsuperior.demo.exceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.devsuperior.demo.exceptionHandler.ApiErro.Field;

@ControllerAdvice
public class ApiErroHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String errorManager = status.getReasonPhrase();

        if (body == null)
            return ResponseEntity.status(status).body(
                    new ApiErro.ApiErroBuilder().status(status.value()).erro(errorManager).build());

        if (body instanceof String)
            return ResponseEntity.status(status).body(
                    new ApiErro.ApiErroBuilder().status(status.value()).erro((String) body).build());

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<Field> fieldErrors = ex.getBindingResult().getFieldErrors().stream().map(fieldError -> {
            String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            return new ApiErro.Field(fieldError.getField(), message);
        }).collect(Collectors.toList());

        ApiErro error = new ApiErro.ApiErroBuilder()
                .timestamp(OffsetDateTime.now())
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .erro("Um ou mais campos estão inválidos")
                .errors(fieldErrors)
                .build();

        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY , request);
    }
}
