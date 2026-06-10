package br.faculdade.models.curso.vestibular;

public class Sala {

    private int numeroSala;
    private int capacidadeSala;
    private String cpf_supervisor;

    public Sala(int numeroSala, int capacidadeSala, String cpf_supervisor) {
        this.numeroSala = numeroSala;
        this.capacidadeSala = capacidadeSala;
        this.cpf_supervisor = cpf_supervisor;
    }

    public int getNumeroSala() {
        return numeroSala;
    }

    public int getCapacidadeSala() {
        return capacidadeSala;
    }

    public String getCpf_supervisor() {
        return cpf_supervisor;
    }

    public void setCpf_supervisor(String cpf_supervisor) {
        this.cpf_supervisor = cpf_supervisor;
    }
}