package com.emailsender.demo.controllerAdvice;

import com.emailsender.demo.domain.model.ResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class AdviceController {
    private static final Logger logger = LoggerFactory.getLogger(AdviceController.class);

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseDTO> exceptionHandler(RuntimeException ex, HttpServletRequest request) {
        logger.error("Erro no endereço: " + request.getRequestURI());
        return ResponseEntity.badRequest().body(new ResponseDTO(ex.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        logger.error("Erro ao tentar validar na rota: " + request.getRequestURI());
        List<String> erros = ex.getBindingResult().getFieldErrors().stream().map(erro -> erro.getDefaultMessage()).toList();
        return ResponseEntity.badRequest().body(ResponseDTO.fromErros(erros, HttpStatus.BAD_REQUEST));
    }
}
