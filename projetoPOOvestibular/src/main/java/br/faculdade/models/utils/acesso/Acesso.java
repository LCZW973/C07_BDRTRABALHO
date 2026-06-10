package br.faculdade.models.utils.acesso;

import java.util.InputMismatchException;

import static br.faculdade.Main.sc;
import static br.faculdade.models.utils.acesso.AcessoDiretor.acessoDiretor;
import static br.faculdade.models.utils.acesso.AcessoSupervisor.acessoSupervisor;
import static br.faculdade.models.utils.acesso.AcessoCorretor.acessoCorretor;
import static br.faculdade.models.utils.acesso.AcessoVestibulando.acessoVestibulando;

public class Acesso {

    public static void iniciarLogin() {
        int funcao = 0 ;
        boolean repetir;

        //captura de excessao ,com loop de repeticao ate ter entrada validada
        do {
            try {
                System.out.println("inserir sua funcao : 1-Supervisor || 2-Corretor || 3-Vestibulando || 4-Diretor");
                repetir = false;
                funcao = sc.nextInt();

                if( 1>funcao || funcao > 4 )
                {
                    System.out.println("Opcao inexistente");
                    repetir = true ;
                }

            } catch (InputMismatchException e) {
                System.out.println("O valor inserido deve ser numerico");
                repetir = true;
                sc.nextLine();
            }
        }while(repetir );


        switch (funcao) {
            case 1:
                acessoSupervisor();
                break;

            case 2:
                acessoCorretor();
                break;

            case 3:
                acessoVestibulando();
                break;

            case 4:
                acessoDiretor();
                break;

            default:
                System.out.println("Função inválida.");
                break;
        }


    }
}