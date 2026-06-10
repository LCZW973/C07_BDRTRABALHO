package br.faculdade.models.curso.vestibular;

import br.faculdade.models.curso.Curso;
import br.faculdade.dao.CursoOfereceVestibularDAO;

import java.util.ArrayList;
import java.util.List;

public class Vestibular {

    private int id;
    private String data_vestibular;

    private List<Materia> materias = new ArrayList<>();

    public Vestibular(int id, String data_vestibular) {
        this.id = id;
        this.data_vestibular = data_vestibular;
    }


    // o vestibular que deve divulgar//
    public void DadosVestibular()
    {
        // o vestibular que deve divulgar//
        try {
            System.out.println("------------------   Dados do Vestibular    ----------------");
            System.out.println("Data: " + data_vestibular);

            System.out.print("Materia: ");
            for (Materia materia : materias) {
                System.out.print(materia.getNome() + " ");
            }
            System.out.println();

            CursoOfereceVestibularDAO cursoOfereceVestibularDAO = new CursoOfereceVestibularDAO();
            List<Curso> cursos = cursoOfereceVestibularDAO.selectVestibularParaCurso(this.id);

            for(Curso curso : cursos) {
                System.out.println("Curso: " + curso.getNome() + " | Vagas: " + curso.getVagas());
            }

        } catch (NullPointerException e) {
            System.out.println("Erro ao encontrar dados dos Vestibular");
        }
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }

    public int getId() {
        return id;
    }

    public String getData_vestibular() {
        return data_vestibular;
    }
}