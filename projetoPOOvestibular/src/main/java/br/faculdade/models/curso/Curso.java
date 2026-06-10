package br.faculdade.models.curso;

import br.faculdade.models.curso.vestibular.Vestibular;

import java.util.ArrayList;
import java.util.List;

public class Curso {

    private int id;
    private String nome;
    private int vagas;

    protected List<Vestibular> vestibulares = new ArrayList<>();

    public Curso(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public void addVestibularParaCurso(Vestibular vestibular){
        this.vestibulares.add(vestibular);
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }

    public String getNome() {
        return nome;
    }

    public int getVagas() {
        return vagas;
    }

    public int getId() {
        return id;
    }
}