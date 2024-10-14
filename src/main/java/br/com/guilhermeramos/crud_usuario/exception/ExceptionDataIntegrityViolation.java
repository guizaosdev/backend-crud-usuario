package br.com.guilhermeramos.crud_usuario.exception;

public class ExceptionDataIntegrityViolation extends RuntimeException {
    public ExceptionDataIntegrityViolation(String message) {
        super(message);
    }
}
