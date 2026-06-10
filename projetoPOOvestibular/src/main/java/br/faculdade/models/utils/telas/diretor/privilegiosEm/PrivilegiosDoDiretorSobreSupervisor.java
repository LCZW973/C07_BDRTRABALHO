package br.faculdade.models.utils.telas.diretor.privilegiosEm;

import br.faculdade.dao.SupervisorDao;
import br.faculdade.models.usuarios.Supervisor;

import static br.faculdade.Main.sc;

public class PrivilegiosDoDiretorSobreSupervisor {

    public static void privilegiosDoDiretorSobreSupervisor() {

        System.out.println("O que deseja fazer com o Supervisor:" +
                "\n1 - Deletar um Supervisor      | 2 - Inserir Supervisor" +
                "\n3 - Mostrar Supervisores");
        int opcao = sc.nextInt();

        SupervisorDao supervisorDao = new SupervisorDao();
        if (opcao == 1) {
            System.out.println("Insira o CPF do supervisor que deseja remover: ");
            sc.nextLine();
            String cpf = sc.nextLine();
            if(supervisorDao.deleteSupervisor(cpf)){
                System.out.println("Supevisor removido com sucesso.");
            }

        } else if (opcao == 2) {
            System.out.println("Insira o CPF do novo supervisor: ");
            sc.nextLine();
            String cpf = sc.nextLine();
            System.out.println("Insira o nome do novo supervisor: ");
            String nome = sc.nextLine();
            System.out.println("Insira o no email do novo supervisor:  ");
            String email = sc.nextLine();

            if(supervisorDao.insertSupervisor(cpf, nome, email)) {
                System.out.println("Supervisor adicionado com sucesso!");
            }

        } else if (opcao == 3) {
            for(Supervisor supervisor : supervisorDao.selectSupervisores()) {
                System.out.println("CPF: " + supervisor.getCpf() +
                        "\nNome: " + supervisor.getNome() + "\nEmail: " + supervisor.getEmail());
                System.out.println("---------------------------");
            }

        } else {
            System.out.println("Não há essa opção.");
        }

    }
}
