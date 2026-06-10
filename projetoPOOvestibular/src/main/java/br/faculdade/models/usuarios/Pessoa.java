package br.faculdade.models.usuarios;

public abstract class Pessoa {

    protected String cpf;
    protected String nome;
    protected String email;

    public Pessoa (String cpf ,String nome , String email)
    {
        this.cpf = cpf;
        this.nome = nome ;
        this.email = email ;
    }

    public void confereSeusDadosComoPessoa()
    {
        System.out.println("Dados do cadastro ");
        System.out.println("Nome : " + nome);
        System.out.println("CPF : " + cpf);
        System.out.println("Email : " + email);
        confereSeusDadosEspecificos();
    }

    public abstract void confereSeusDadosEspecificos();
    public abstract void atualizaEmail(String novoEmail);

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}