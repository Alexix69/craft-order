package com.classic.craftorder.presentacion.controladores;

import com.classic.craftorder.presentacion.dto.response.ErrorResponseDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponseDto> manejarNoEncontrado(NoSuchElementException ex) {
        ErrorResponseDto error = new ErrorResponseDto();
        error.setMensaje(ex.getMessage());
        error.setCodigo(HttpStatus.NOT_FOUND.name());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseDto> manejarIntegridad(DataIntegrityViolationException ex) {
        ErrorResponseDto error = new ErrorResponseDto();
        error.setMensaje("El correo ya está registrado");
        error.setCodigo(HttpStatus.CONFLICT.name());
        error.setCampo("correo");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> manejarValidacion(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldErrors().get(0);
        ErrorResponseDto error = new ErrorResponseDto();
        error.setMensaje(fieldError.getDefaultMessage());
        error.setCodigo(HttpStatus.BAD_REQUEST.name());
        error.setCampo(fieldError.getField());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDto> manejarRuntime(RuntimeException ex) {
        ErrorResponseDto error = new ErrorResponseDto();
        error.setMensaje(ex.getMessage());
        error.setCodigo(HttpStatus.BAD_REQUEST.name());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
