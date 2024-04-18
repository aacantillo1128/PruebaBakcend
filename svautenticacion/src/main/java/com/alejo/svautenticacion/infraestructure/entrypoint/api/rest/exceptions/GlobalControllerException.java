package com.alejo.svautenticacion.infraestructure.entrypoint.api.rest.exceptions;

import com.alejo.svautenticacion.domain.model.domain.exceptios.ForbiddenException;
import com.alejo.svautenticacion.domain.model.domain.exceptios.UserDoesNotExistException;
import com.alejo.svautenticacion.infraestructure.entrypoint.api.rest.utils.Utilities;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GlobalControllerException extends ResponseEntityExceptionHandler {


    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ApiError> handleDuplicateEmailOrUsername(SQLIntegrityConstraintViolationException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(createApiError(Utilities.EXISTING_EMAIL_OR_USERNAME));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleIncorrectCredentials(AuthenticationException ex) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(createApiError(Utilities.INCORRECT_CREDENTIALS));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFound(UserNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(createApiError(Utilities.USER_NOT_FOUND));
    }

    @ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity<ApiError> handleUserNotFound(UserDoesNotExistException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(createApiError(ex.getMessage()));
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ApiError> handleOperationNotAllowed(ForbiddenException ex) {

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(createApiError(ex.getMessage()));
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiError> handleExpiredJwt(ExpiredJwtException ex) {

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(createApiError(ex.getMessage()));
    }


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        return ResponseEntity.status(statusCode).headers(headers).body(createApiError(ex.getMessage()));
    }

    private ApiError createApiError(String message){
        return new ApiError(message);
    }
}
