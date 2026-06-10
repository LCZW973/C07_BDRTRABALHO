package br.faculdade.models.utils.telas;

import br.faculdade.dao.SupervisorDao;
import br.faculdade.models.exceptions.FalhaNoEmailException;
import br.faculdade.models.usuarios.Supervisor;
import br.faculdade.models.usuarios.Vestibulando;

import java.util.*;

import static br.faculdade.Main.sc;

public class TelaSupervisor {
    public static void telaSupervisor(Supervisor supervisor) {
        SupervisorDao supervisorDao = new SupervisorDao();
        List<Vestibulando> lista ;
        lista= supervisorDao.selectCpfVestibulando(supervisor);

        supervisor.setListVestibulando(lista);

        int opcao = -1;

        while (opcao != 0) {

            try {

                System.out.println("================== Página do Supervisor ==================");
                System.out.println("1 - Acessar seus dados");
                System.out.println("2 - Ver vestibulandos da sala");
                System.out.println("3 - Alterar e-mail");
                System.out.println("0 - Finalizar sessão");

                opcao = sc.nextInt();

                if (opcao == 1) {

                    supervisor.confereSeusDadosComoPessoa();

                } else if (opcao == 2) {

                    supervisor.confereVestibulandosDaSala();

                } else if (opcao == 3) {

                    sc.nextLine();

                    System.out.println("Informe seu novo e-mail:");
                    String novoEmail = sc.nextLine();

                    try {

                        supervisor.atualizaEmail(novoEmail);

                        if (supervisorDao.updateSupervisor(supervisor)) {
                            System.out.println("E-mail alterado com sucesso!");
                        } else {
                            throw new FalhaNoEmailException("Falha ao alterar e-mail.");
                        }

                    } catch (FalhaNoEmailException e) {
                        System.out.println(e.getMessage());
                    }

                } else if (opcao == 0) {

                    System.out.println("Encerrando sessão...");

                } else {

                    System.out.println("Opção inválida.");

                }

            } catch (InputMismatchException e) {

                System.out.println("Opção inválida.");

                opcao = -1;
                sc.nextLine();
            }
        }

    }
}
