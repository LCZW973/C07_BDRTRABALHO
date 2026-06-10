package br.faculdade.models.utils.telas.diretor.privilegiosEm;

import br.faculdade.dao.SalaDAO;
import br.faculdade.models.curso.vestibular.Sala;

import static br.faculdade.Main.sc;

public class PrivilegiosDoDiretorSobreSala {

    public static void privilegiosDoDiretorSobreSala() {

        System.out.println("O que deseja fazer com as Salas:" +
                "\n1 - Alterar Vagas Disponíveis  | 2 - Deletar uma Sala" +
                "\n3 - Inserir Sala               | 4 -  Mostrar Salas" +
                "\n5 - Alocar Supervisor");
        int opcao = sc.nextInt();

        SalaDAO salaDAO = new SalaDAO();
        if (opcao == 1) {
            System.out.println("Insira o numero da sala que deseja alterar: ");
            int numero = sc.nextInt();
            System.out.println("Insira a nova quantidade de vagas disponíveis na sala: ");
            int vagas = sc.nextInt();

            if (salaDAO.updateSala(vagas, numero)) {
                System.out.println("Vagas da sala alterada com sucesso.");
            }

        } else if (opcao == 2) {
            System.out.println("Insira o numero da sala que deseja remover do sistema: ");
            int numero = sc.nextInt();

            if (salaDAO.deleteSala(numero)) {
                System.out.println("Sala removida com sucesso.");
            }

        } else if (opcao == 3) {
            System.out.println("Insira o numero da sala: ");
            int numero = sc.nextInt();
            System.out.println("Insira a quantidade de alunos que cabem na sala: ");
            int vagas = sc.nextInt();
            System.out.println("Insira o CPF do supervisor que será responsável pela sala: ");
            sc.nextLine();
            String cpf = sc.nextLine();

            if (salaDAO.insertSala(new Sala(numero, vagas, cpf))) {
                System.out.println("Sala inserida com sucesso.");
            }

        } else if (opcao == 4) {
            for (Sala sala : salaDAO.selectTodasAsSalas()) {
                System.out.println("Numero: " + sala.getNumeroSala() +
                        "\nCPF do Supervisor: " + sala.getCpf_supervisor() +
                        "\nCapacidade: " + sala.getCapacidadeSala());
                System.out.println("---------------------------");
            }

        } else if(opcao == 5){
            System.out.println("Digite o ID da sala em que deseja alocar um novo supervisor: ");
            int id = sc.nextInt();
            System.out.println("Digite o CPF do supervisor que deseja alocar para essa sala: ");
            sc.nextLine();
            String cpf = sc.nextLine();

            if(salaDAO.updateSalaAlocandoUmNovoSupervisor(cpf, id)) {
                System.out.println("Supervisor alocado com sucesso na sala.");
            }
        }else {
            System.out.println("Não há essa opção.");
        }

    }
}
