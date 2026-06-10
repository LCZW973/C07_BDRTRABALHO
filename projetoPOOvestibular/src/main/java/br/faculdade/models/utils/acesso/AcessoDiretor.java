package br.faculdade.models.utils.acesso;

import br.faculdade.models.utils.telas.diretor.TelaDiretor;

import static br.faculdade.Main.sc;

public class AcessoDiretor {

    public static void acessoDiretor() {

        System.out.println("Informe a Senha: ");
        String senha;

        sc.nextLine();
        senha = sc.nextLine();

        if(senha.equals("Inatel")) {
            TelaDiretor.telaDiretor();
        } else {
            System.out.println("Senha errada.");
        }
    }
}
