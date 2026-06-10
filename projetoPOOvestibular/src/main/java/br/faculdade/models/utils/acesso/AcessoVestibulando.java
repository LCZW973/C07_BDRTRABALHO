package br.faculdade.models.utils.acesso;

import br.faculdade.models.exceptions.UsuarioVazioException;
import br.faculdade.models.usuarios.Vestibulando;
import br.faculdade.models.utils.CadastrarVestibulando;
import br.faculdade.models.utils.login.LoginVestibulando;
import br.faculdade.models.utils.telas.TelaVestibulando;

import java.util.InputMismatchException;

import static br.faculdade.Main.sc;

public class AcessoVestibulando {

    public static void acessoVestibulando(){

        Vestibulando vestibulando = null;

        System.out.println("Bem Vindo Vestibulando!\nJá é cadastrado?" +
                "\n1 - Sim | 2 - Não");


        int opcao = 0;

        while (opcao != 1 && opcao != 2) {
            try {
                opcao = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Erro: " + e);
                System.out.println("Opção Inválida: Valor não numérico.");

                sc.nextLine();
            }
        }

        if(opcao == 1) {
            vestibulando = LoginVestibulando.loginVestibulando();
        } else if (opcao == 2) {
            vestibulando = CadastrarVestibulando.cadastrarVestibulando();
        }

        try {
            if (vestibulando != null) {
                TelaVestibulando.TelaVestibulando(vestibulando);
            } else {
                throw new UsuarioVazioException("Não foi possível completar seu acesso.");
            }
        } catch (UsuarioVazioException e) {
            System.out.println(e.getMessage());
        }

    }
}
