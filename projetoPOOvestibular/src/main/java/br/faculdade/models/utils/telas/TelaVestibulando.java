package br.faculdade.models.utils.telas;

import br.faculdade.dao.ProvaDAO;
import br.faculdade.dao.VestibulandoDAO;
import br.faculdade.models.curso.vestibular.Prova;
import br.faculdade.models.exceptions.FalhaNoEmailException;
import br.faculdade.models.exceptions.NotaInvalidaException;
import br.faculdade.models.exceptions.NotaNaoEncontradaException;
import br.faculdade.models.usuarios.Vestibulando;

import java.util.InputMismatchException;

import static br.faculdade.Main.sc;

public class TelaVestibulando {

    public static void TelaVestibulando(Vestibulando vestibulando) {

        VestibulandoDAO vestibulandoDAO = new VestibulandoDAO();

        int opcao = -1;

        while(opcao != 0) {

            try {

                System.out.println("================== Página do Vestibulando ==================\n" +
                        "1 - Acessar seus dados    | 2 - Acessar dados do vestibular \n" +
                        "3 - Alterar e-mail        | 4 - Verificar Nota              \n" +
                        "5 - Excluir Usuário       | 0 - Finalizar Sessão");

                opcao = sc.nextInt();

                if (opcao == 1) {
                    try {
                        vestibulando.confereSeusDadosComoPessoa();
                    } catch (NullPointerException e) {
                        System.out.println("Erro ao acessar dados");
                    }

                } else if (opcao == 2) {

                    vestibulando.confereDadosVestibular();

                } else if (opcao == 3) {

                    String novoEmail;
                    sc.nextLine(); // Para evitar bugs de entrada

                    System.out.println("Informe seu novo e-mail: ");

                    try {
                        novoEmail = sc.nextLine();
                        vestibulando.atualizaEmail(novoEmail);

                        // Estrutura para alterar o e-mail
                        if (vestibulandoDAO.updateVestibulando(vestibulando)) {
                            System.out.println("E-mail alterado com sucesso!");
                        } else {
                            throw new FalhaNoEmailException("Falha ao alterar e-mail");
                        }

                    } catch (InputMismatchException | FalhaNoEmailException e) {
                        System.out.println("Erro: " + e);
                        System.out.println("Opção de e-mail inválida!");
                    }

                } else if (opcao == 4) {

                    try {
                        vestibulando.pesquisaNota();
                    } catch (NotaInvalidaException | NotaNaoEncontradaException e) {
                        System.out.println(e.getMessage());
                    }

                } else if (opcao == 5) {

                    vestibulandoDAO.deleteVestibulando(vestibulando.getCpf());

                    ProvaDAO provaDAO = new ProvaDAO();
                    for(Prova prova : vestibulando.getProvas()) {
                        provaDAO.deleteProva(prova);
                    }
                    opcao = 0;
                    System.out.println("Excluindo Usuário e Encerrando sessão...");

                } else if (opcao == 0) {
                    System.out.println("Encerrando sessão...");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: " + e);
                System.out.println("Opção inválida!");

                // Ajustes para não 'bugar'
                opcao = -1;
                sc.nextLine();
            }
        }
    }
}
