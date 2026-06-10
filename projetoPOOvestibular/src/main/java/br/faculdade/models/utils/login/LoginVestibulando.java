package br.faculdade.models.utils.login;

import br.faculdade.dao.*;
import br.faculdade.models.curso.vestibular.Vestibular;
import br.faculdade.models.usuarios.Vestibulando;

import java.util.List;

import static br.faculdade.Main.sc;

public class LoginVestibulando {

    public static Vestibulando loginVestibulando(){

        String loginCPF;

        Vestibulando vestibulando = null;

        System.out.println("Insira o seu CPF: ");


        try {
            sc.nextLine();
            loginCPF = sc.nextLine();

            VestibulandoDAO retornandoVestibulando = new VestibulandoDAO();
            VestibulandoPrestaVestibularDAO vestibulandoPrestaVestibularDAO = new VestibulandoPrestaVestibularDAO();
            VestibularDAO vestibularDAO = new VestibularDAO();
            MateriaDAO materiaDAO = new MateriaDAO();

            vestibulando = retornandoVestibulando.selectVestibulando(loginCPF); // Pegando os dados gerais do vestibulando

            // Recuperando os vestibulares em que o vestibulando está escrito

            List<Integer> ids_vestibulares =
                    vestibulandoPrestaVestibularDAO.selectIdVestibularesPrestados(loginCPF); // Lista de IDS dos Vestibulares
            for(int id : ids_vestibulares ) {
                Vestibular vestibular = vestibularDAO.selectVestibular(id);

                vestibulando.addVestibular(vestibular); // Adicionando os vestibulares em Vestibulando
                vestibular.setMaterias(materiaDAO.selectMaterias(vestibular.getId())); // Adicionando Matérias Para o Vestibular
            }

            ProvaDAO provaDAO = new ProvaDAO();
            vestibulando.setProvas(provaDAO.selectProva(loginCPF));

        } catch (NullPointerException e) {
            System.out.println("Erro: " + e);
        }

        return vestibulando;
    }

}