package br.faculdade.models.exceptions;

public class UsuarioVazioException extends RuntimeException {
    public UsuarioVazioException(String message) {
        super(message);
    }
}
