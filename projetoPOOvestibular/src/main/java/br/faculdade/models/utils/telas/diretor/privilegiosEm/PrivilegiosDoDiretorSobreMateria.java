package br.faculdade.models.utils.telas.diretor.privilegiosEm;

import br.faculdade.dao.MateriaDAO;
import br.faculdade.models.curso.vestibular.Materia;

import static br.faculdade.Main.sc;

public class PrivilegiosDoDiretorSobreMateria {

    public static void privilegiosDoDiretorSobreMateria() {

        System.out.println("O que deseja fazer com as Matérias:" +
                "\n1 - Alterar nome de uma Matéria | 2 - Deletar uma Matéria" +
                "\n3 - Inserir Matéria             | 4 -  Mostrar Matérias");
        int opcao = sc.nextInt();

        MateriaDAO materiaDAO = new MateriaDAO();
        if(opcao == 1) {
            System.out.println("Insira o ID da matéria que deseja alterar o nome: ");
            int id = sc.nextInt();
            System.out.println("Insira o novo nome: ");
            sc.nextLine();
            String materia = sc.nextLine();

            if(materiaDAO.updateMaterias(materia, id)) {
                System.out.println("Nome da matéria alterado com sucesso.");
            }

        } else if (opcao == 2) {
            System.out.println("Insira o ID matéria que deseja remover do sistema: ");
            int id = sc.nextInt();

            if(materiaDAO.deleteMaterias(id)) {
                System.out.println("Materia removida com sucesso.");
            }

        } else if(opcao == 3) {
            System.out.println("Insira o ID do vestibular ao qual a matéria pertence: ");
            int id = sc.nextInt();
            System.out.println("Insira o nome da matéria: ");
            sc.nextLine();
            String materia = sc.nextLine();

            if(materiaDAO.insertMaterias(id, materia)) {
                System.out.println("Matéria adicionada com sucesso.");
            }

        } else if (opcao == 4) {

            for(Materia materia : materiaDAO.selectTodasAsMaterias()) {
                System.out.println("ID: " + materia.getId() + "; Nome: " + materia.getNome());
            }
            System.out.println("---------------------------");

        } else {
            System.out.println("Não há essa opção.");
        }

    }
}
