package br.faculdade.models.exceptions;

public class NotaNaoEncontradaException extends RuntimeException {
    public NotaNaoEncontradaException(String message) {
        super(message);
    }
}