package br.faculdade.models.curso.vestibular;

public class Resultado {

    protected Float nota;

    public Resultado(Float nota) {
        this.nota = nota;
    }

    public Float getNota() {
        return nota;
    }

    public void setNota(Float nota) {
        this.nota = nota;
    }
}