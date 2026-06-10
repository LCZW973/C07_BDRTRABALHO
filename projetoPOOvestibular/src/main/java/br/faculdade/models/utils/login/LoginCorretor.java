package br.faculdade.models.utils.login;

import br.faculdade.dao.CorretorDAO;
import br.faculdade.models.usuarios.Corretor;

import static br.faculdade.Main.sc;

public class LoginCorretor {

    public static Corretor loginCorretor() {
        String loginCPF;
        String loginEmail;
        Corretor corretor;
        CorretorDAO corretorDAO = new CorretorDAO();

        sc.nextLine();

        System.out.println("Insira o seu email: ");
        loginEmail = sc.nextLine();

        System.out.println("Insira o seu CPF: ");
        loginCPF = sc.nextLine();

        corretor = corretorDAO.autentica(loginCPF, loginEmail);

        return corretor;
    }
}