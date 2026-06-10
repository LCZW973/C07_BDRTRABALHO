package br.faculdade.models.utils.telas.diretor.privilegiosEm;

import br.faculdade.dao.CursoDAO;

import static br.faculdade.Main.sc;

public class PrivilegiosDoDiretorSobreCurso {

    public static void privilegiosDoDiretorSobreCurso() {
        System.out.println("O que deseja fazer com o Curso:" +
                    "\n1 - Alterar Nome do Curso | 2 - Deletar um Curso" +
                    "\n3 - Inserir Curso         | 4 - Mostrar Cursos");

        int opcao = sc.nextInt();
        CursoDAO cursoDAO = new CursoDAO();
        if(opcao == 1) {
            System.out.println("Insira o ID do curso que deseja alterar o nome:");
            int id = sc.nextInt();
            System.out.println("Insira o novo nome do curso: ");
            sc.nextLine();
            String nome = sc.nextLine();
            if(cursoDAO.updateNomeCurso(nome, id)) {
                System.out.println("Nome do Curso alterado com sucesso!");
            }

        } else if (opcao == 2) {
            System.out.println("Insira o ID do Curso: ");
            int id = sc.nextInt();
            if(cursoDAO.deleteCurso(id)){
                System.out.println("Curso removido com sucesso!");
            }

        } else if(opcao == 3) {
            System.out.println("Insira o nome do novo Curso: ");
            sc.nextLine();
            String nome = sc.nextLine();
            if(cursoDAO.insertCurso(nome)) {
                System.out.println("Curso inserido com sucesso!");
            }

        } else if (opcao == 4) {
            cursoDAO.selectTodosCursos().forEach((index, curso) ->{
                System.out.println("Nome: " + curso.getNome() + "; ID: " + curso.getId());
            });
            System.out.println("--------------------");
        } else {
            System.out.println("Não há essa opção.");
        }
    }
}
