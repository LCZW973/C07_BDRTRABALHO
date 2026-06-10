package br.faculdade.models.curso.vestibular;

public class Prova {

    private int id;
    private int edicao;
    private String cpf_corretor;
    private Resultado resultado = null;

    public Prova(int edicao, String cpf_corretor) {
        this.id = id;
        this.edicao = edicao;
        this.cpf_corretor = cpf_corretor;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public int getId() {
        return id;
    }

    public int getEdicao() {
        return edicao;
    }

    public String getCpf_corretor() {
        return cpf_corretor;
    }

    public void setId(int id) {
        this.id = id;
    }
}
