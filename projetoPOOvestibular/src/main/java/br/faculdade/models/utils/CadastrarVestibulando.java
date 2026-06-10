package br.faculdade.models.utils;

import br.faculdade.models.exceptions.SemSalasDisponiveisException;
import br.faculdade.models.usuarios.Vestibulando;

import java.util.InputMismatchException;

import static br.faculdade.Main.sc;

public class CadastrarVestibulando {

    public static Vestibulando cadastrarVestibulando(){
        Vestibulando vestibulando = null;

        System.out.println("Vamos realizar seu cadastro!");

        try {
            sc.nextLine();

            System.out.println("Informe seu CPF: ");
            String cpf = sc.nextLine();
            System.out.println("Informe seu Nome: ");
            String nome = sc.nextLine();
            System.out.println("Informe seu E-mail: ");
            String email = sc.nextLine();

            vestibulando = new Vestibulando(cpf, nome, email);

            // Tenta alocar o vestibulando em alguma sala, se conseguir já armazena no BD, se não conseguir retorna um erro
            if(!AlocaEmSala.alocaEmSala(vestibulando)) {
                throw new SemSalasDisponiveisException("Não há mais vagas disponíveis, tente novamente mais tarde ou entre em contato conosco.");
            }

            System.out.println("Cadastro Realizado!");

            SelecionandoVestibulares.selecionandoVestibulares(vestibulando);

        } catch (InputMismatchException | NullPointerException e) {
            System.out.println("Erro: " + e);
        } catch (SemSalasDisponiveisException e) {
            System.out.println(e.getMessage());
            vestibulando = null;
            // Se não foi possível alocar o vestibulando em alguma sala e, consequentemente,
            // não terminou seu cadastro, ele "nullifica" o vestibulando
        }

        return vestibulando;
    }
}
