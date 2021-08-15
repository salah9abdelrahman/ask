package com.salah.ask.exception;

import com.salah.ask.exception.custom.EntityAlreadyExists;
import com.salah.ask.exception.custom.EntityNotFoundException;
import com.salah.ask.exception.custom.InvalidJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

        /*
         * MethodArgumentNotValidException is thrown when argument annotated with @Valid
         * failed validation
         */
        @Override
        protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                        HttpHeaders headers, HttpStatus status, WebRequest request) {
                List<String> errors = new ArrayList<String>();
                for (FieldError error : ex.getBindingResult().getFieldErrors()) {
                        errors.add(error.getField() + ": " + error.getDefaultMessage());
                }
                for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
                        errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
                }

                ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), errors);
                return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
        }

        @Override
        protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                        HttpHeaders headers, HttpStatus status, WebRequest request) {
                StringBuilder builder = new StringBuilder();
                builder.append(ex.getMethod());
                builder.append(" method is not supported for this request. Supported methods are ");
                ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

                ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED, ex.getLocalizedMessage(),
                                builder.toString());
                return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
        }

        /*
         * reports the result of constraint violations
         */

        @ExceptionHandler({ ConstraintViolationException.class })
        public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
                List<String> errors = new ArrayList<String>();
                for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
                        errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
                                        + violation.getMessage());
                }

                ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
                return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
        }

        @ExceptionHandler({ EntityNotFoundException.class })
        public ResponseEntity<Object> handleNotFoundEntity(EntityNotFoundException ex) {

                ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), "Not found");
                return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
        }

        @ExceptionHandler({ Exception.class })
        public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
                ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(),
                                "error occurred");
                return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
        }

        @ExceptionHandler({ EntityAlreadyExists.class })
        public ResponseEntity<Object> handleAlreadyExistedEntity(EntityAlreadyExists ex) {
                ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), "already exists");
                return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
        }

        @ExceptionHandler({ InvalidJwtException.class })
        public ResponseEntity<Object> handleInvalidJwtException(InvalidJwtException ex) {
                ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), "Invalid token");
                return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
        }
}
