package br.faculdade.models.exceptions;

public class FalhaNoEmailException extends RuntimeException {
    public FalhaNoEmailException(String message) {
        super(message);
    }
}
