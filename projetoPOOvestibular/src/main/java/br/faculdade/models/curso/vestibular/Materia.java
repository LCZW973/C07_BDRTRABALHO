package br.faculdade.models.curso.vestibular;

public class Materia {

    private int id;
    private String nome;

    public Materia(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }
}