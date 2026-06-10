package br.faculdade.models.utils.acesso;

import br.faculdade.models.exceptions.UsuarioVazioException;
import br.faculdade.models.usuarios.Supervisor;
import br.faculdade.models.utils.login.LoginSupervisor;
import br.faculdade.models.utils.telas.TelaSupervisor;

public  class AcessoSupervisor {

    public static void acessoSupervisor(){

        Supervisor supervisor = null;

        System.out.println("Bem Vindo Supervisor");

        supervisor = LoginSupervisor.loginSupervisor();

        try {
            if (supervisor != null) {
                TelaSupervisor.telaSupervisor(supervisor);
            } else {
                throw new UsuarioVazioException("Não foi possível completar seu acesso.");
            }
        } catch (UsuarioVazioException e) {
            System.out.println(e.getMessage());
        }

    }

}