package br.faculdade.models.utils.telas.diretor.privilegiosEm;

import br.faculdade.dao.CorretorDAO;
import br.faculdade.models.usuarios.Corretor;

import static br.faculdade.Main.sc;

public class PrivilegiosDoDiretorSobreCorretor {

    public static void privilegiosDoDiretorSobreCorretor() {

        System.out.println("O que deseja fazer com o Corretor:" +
                "\n1 - Deletar um Corretor        | 2 - Inserir Corretor" +
                "\n3 - Mostrar Corretores");
        int opcao = sc.nextInt();

        CorretorDAO corretorDAO = new CorretorDAO();
        if(opcao == 1) {
            System.out.println("Insira o CPF do corretor que deseja remover do sistema: ");
            sc.nextLine();
            String cpf = sc.nextLine();

            if(corretorDAO.deleteCorretor(cpf)) {
                System.out.println("Corretor removido com sucesso.");
            }

        } else if (opcao == 2) {
            System.out.println("Insira o CPF do corretor que deseja adicionar ao sistema: ");
            sc.nextLine();
            String cpf = sc.nextLine();
            System.out.println("Insira o nome do corretor: ");
            String nome = sc.nextLine();
            System.out.println("Insira o email do corretor: ");
            String email = sc.nextLine();

            if(corretorDAO.InserirCorretor(new Corretor(cpf, nome, email))) {
                System.out.println("Corretor adicionado com sucesso.");
            }

        } else if(opcao == 3) {
            for(Corretor corretor : corretorDAO.selectCorretores()) {
                System.out.println("CPF: " + corretor.getCpf() +
                        "\nNome: " + corretor.getNome() +
                        "\nEmail: " + corretor.getEmail());
                System.out.println("---------------------------");
            }

        } else {
            System.out.println("Não há essa opção.");
        }

    }
}
