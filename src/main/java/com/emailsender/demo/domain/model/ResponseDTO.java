package com.emailsender.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {
    private String message;
    private HttpStatus statusCode;

    private List<String> listaErros;

    public ResponseDTO(String message, HttpStatus statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public ResponseDTO(HttpStatus statusCode, List<String> listaErros) {
        this.statusCode = statusCode;
        this.listaErros = listaErros;
    }

    public ResponseDTO() {
    }

    public String getMessage() {
        return message;
    }
    public HttpStatus getStatusCode() {return statusCode;}
    public List<String> getListaErros() {return listaErros;}

    public static ResponseDTO fromErros(List<String> erros, HttpStatus statusCode) {
        if(erros.size() > 1) {
            return new ResponseDTO(statusCode, erros);
        }
        return new ResponseDTO(erros.get(0), statusCode);
    }
}
