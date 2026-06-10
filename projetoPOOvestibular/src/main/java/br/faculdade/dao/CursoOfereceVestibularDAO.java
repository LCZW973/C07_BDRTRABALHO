package br.faculdade.dao;

import br.faculdade.models.curso.Curso;
import br.faculdade.models.curso.vestibular.Vestibular;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoOfereceVestibularDAO extends ConnectionDao{

    public List<Curso> selectVestibularParaCurso(int id_vestibular){
        List<Curso> cursos = new ArrayList<>();
        CursoDAO cursoDAO = new CursoDAO();
        connectToDb();
        String sql = "SELECT * FROM curso_oferece_vestibular WHERE id_vestibular=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id_vestibular);
            rs = pst.executeQuery();
            while (rs.next()) {
                Curso curso = cursoDAO.selectCurso(id_vestibular);
                curso.setVagas(rs.getInt("vagas"));
                cursos.add(curso);
            }
        } catch (SQLException e){
            System.out.println("Erro ao buscar Cursos relacionados ao Vestibular: " + e.getMessage());
        } finally {
            try {
                if(rs != null) rs.close();
                if(pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return cursos;
    }



    public Vestibular selectCursoOfereceVestibular(int id_curso){
        Vestibular vestibular = null;
        VestibularDAO vestibularDAO = new VestibularDAO();
        connectToDb();
        String sql = "SELECT * FROM curso_oferece_vestibular WHERE id_curso=? LIMIT 1";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id_curso);
            rs = pst.executeQuery();
            if(rs.next()) {
                vestibular = vestibularDAO.selectVestibular(id_curso);
            }
        } catch (SQLException e){
            System.out.println("Erro ao buscar Vestibular relacionado ao Curso: " + e.getMessage());
        } finally {
            try {
                if(rs != null) rs.close();
                if(pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return vestibular;
    }
}