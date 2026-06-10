package br.faculdade.models.usuarios;
import br.faculdade.models.curso.vestibular.Sala;
import br.faculdade.dao.SalaDAO;

import java.util.List;

public class Supervisor extends Pessoa {

    Sala sala ;
    List<Vestibulando>  listVestibulando ;

    public Supervisor(String cpf, String nome, String email, int id) {
        super(cpf, nome, email);
        SalaDAO  salaDao = new SalaDAO();
        sala = salaDao.selectSalaSupervisor(cpf);
    }

    @Override
    public void confereSeusDadosEspecificos()
    {
        try {
            System.out.println("Responsavel pela sala :" + sala.getNumeroSala());
            System.out.println("Quantidade de alunos suportados pela sala :" + sala.getCapacidadeSala());
        } catch (NullPointerException e) {
            System.out.println("Erro ao recuperar dados.");
        }
    }

    @Override
    public void atualizaEmail(String novoEmail) {
        setEmail(novoEmail);
    }

    public void confereVestibulandosDaSala( )
    {

        System.out.println();
        System.out.println("Lista de vestibulando da sala ");
        for(Vestibulando elemento : listVestibulando)
        {
            System.out.println("nome : "+elemento.getNome()+"   cpf : " + elemento.getCpf());
        }
    }

    public void setListVestibulando(List<Vestibulando> list ){
        this.listVestibulando = list ;
    }

}
