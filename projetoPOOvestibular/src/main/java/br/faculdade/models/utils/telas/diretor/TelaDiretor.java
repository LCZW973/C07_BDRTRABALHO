package br.faculdade.models.utils.telas.diretor;
import br.faculdade.models.utils.telas.diretor.privilegiosEm.*;

import java.util.InputMismatchException;

import static br.faculdade.Main.sc;

public class TelaDiretor {

    public static void telaDiretor() {
        System.out.println("Bem-Vindo Diretor!");

        int opcao = -1;

        while(opcao != 0) {

            try {

                System.out.println("Selecione qual informação do sistema você deseja analisar: " +
                        "\n1 - Curso        | 2 - Vestibular" +
                        "\n3 - Supervisor   | 4 - Corretor" +
                        "\n5 - Sala         | 6 - Materia" +
                        "\n0 - Encerrar Sessão");

                opcao = sc.nextInt();

                if (opcao == 1) { // Curso

                    PrivilegiosDoDiretorSobreCurso.privilegiosDoDiretorSobreCurso();

                } else if (opcao == 2) { // Vestibular

                    PrivilegiosDoDiretorSobreVestibular.privilegiosDoDiretorSobreVestibular();

                } else if (opcao == 3) { // Supervisor

                    PrivilegiosDoDiretorSobreSupervisor.privilegiosDoDiretorSobreSupervisor();

                } else if (opcao == 4) { // Corretor

                    PrivilegiosDoDiretorSobreCorretor.privilegiosDoDiretorSobreCorretor();

                } else if (opcao == 5) { // Salas

                    PrivilegiosDoDiretorSobreSala.privilegiosDoDiretorSobreSala();

                } else if (opcao == 6) { // Matérias

                    PrivilegiosDoDiretorSobreMateria.privilegiosDoDiretorSobreMateria();

                } else if (opcao == 0) {
                    System.out.println("Encerrando sessão...");
                } else {
                    System.out.println("Não há essa opção.");
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
