package br.faculdade.models.usuarios;
import br.faculdade.models.curso.vestibular.Prova;
import br.faculdade.models.curso.vestibular.Sala;
import br.faculdade.models.curso.vestibular.Vestibular;
import br.faculdade.models.exceptions.NotaInvalidaException;
import br.faculdade.models.exceptions.NotaNaoEncontradaException;
import br.faculdade.models.interfaces.ConsultarNota;

import java.util.ArrayList;
import java.util.List;

public class Vestibulando extends Pessoa implements ConsultarNota {

    private List<Vestibular> vestibulares = new ArrayList<>();
    private List<Prova> provas = new ArrayList<>();
    private Sala sala;

    //ao logar um vestibulando,o vestibulando deve ser capaz de puxar seus dados como "pessoa" do banco e criar o construtor//
    public Vestibulando (String cpf ,String nome , String email)
    {
        super(cpf,nome,email);
    }
    //o vestibulando em especifico ao checar seus dados deve ser capaz de puxar do banco seus dados como vestibulando //
    @Override
    public void confereSeusDadosEspecificos()
    {
        try {
            System.out.println("Alocado para a sala " + this.sala.getNumeroSala());
        } catch (NullPointerException e) {
            System.out.println("Erro ao recuperar sala.");
        }
    }

    //adicionar a modificacao no bd dps
    @Override
    public void atualizaEmail(String novoemail)
    {
        setEmail(novoemail);
    }

    //o aluno pode ver os dados do vestibular//
    public void confereDadosVestibular()
    {
        for(Vestibular vestibular : vestibulares) {
            vestibular.DadosVestibular();
        }
    }

    //e preciso considerar a possibilidade do aluno checar a nota antes do supervisor a postar,um possivel caso de erro//
    @Override
    public void pesquisaNota()
    {
        for(Prova prova : this.provas) {
            try {
                if (prova.getResultado() != null && prova.getResultado().getNota() >= 0) {
                    System.out.println("Nota: " + prova.getResultado().getNota());
                } else if (prova.getResultado() == null) {
                    throw new NotaNaoEncontradaException("Nota não encontrada.");
                } else if (prova.getResultado().getNota() < 0) {
                    throw new NotaInvalidaException("Nota com valor inválido.");
                }
            } catch (NullPointerException e){
                System.out.println("Nota não encontrada.");
            }
        }
    }

    public Sala getSala() throws NullPointerException{
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public void addVestibular(Vestibular vestibular) {
        vestibulares.add(vestibular);
    }

    public void addProva(Prova prova) {
        provas.add(prova);
    }

    public List<Prova> getProvas() {
        return provas;
    }

    public void setProvas(List<Prova> provas) {
        this.provas = provas;
    }
}
