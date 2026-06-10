package br.faculdade.models.utils.telas;

import br.faculdade.dao.CorretorDAO;
import br.faculdade.models.exceptions.FalhaNoEmailException;
import br.faculdade.models.usuarios.Corretor;

import java.util.InputMismatchException;

import static br.faculdade.Main.sc;

public class TelaCorretor {
    public static void telaCorretor(Corretor corretor) {
        CorretorDAO corretorDAO = new CorretorDAO();

        int opcao = -1;

        while (opcao != 0) {

            try {

                System.out.println("================== Página do Corretor ==================");
                System.out.println("1 - Acessar seus dados");
                System.out.println("2 - Lançar notas por arquivo");
                System.out.println("3 - Alterar e-mail");
                System.out.println("0 - Finalizar sessão");

                opcao = sc.nextInt();

                if (opcao == 1) {

                    corretor.confereSeusDadosComoPessoa();

                } else if (opcao == 2) {

                    sc.nextLine();

                    System.out.println("Informe o caminho do arquivo de notas (ex: notas.csv):");
                    String caminho = sc.nextLine();

                    corretor.lancarNotasPorArquivo(caminho);

                } else if (opcao == 3) {

                    sc.nextLine();

                    System.out.println("Informe seu novo e-mail:");
                    String novoEmail = sc.nextLine();

                    try {

                        corretor.atualizaEmail(novoEmail);

                        if (corretorDAO.updateCorretor(corretor)) {
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