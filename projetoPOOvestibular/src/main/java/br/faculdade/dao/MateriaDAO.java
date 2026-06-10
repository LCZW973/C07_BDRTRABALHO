package br.faculdade.dao;

import br.faculdade.models.curso.vestibular.Materia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MateriaDAO extends ConnectionDao{

    public List<Materia> selectMaterias(int id_vestibular){
        List<Materia> materias = new ArrayList<>();
        connectToDb();
        String sql = "SELECT * FROM materia WHERE id_vestibular=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id_vestibular);
            rs = pst.executeQuery();
            while (rs.next()) {
                Materia materia = new Materia(
                        rs.getInt("id"),
                        rs.getString("materia")
                );
                materias.add(materia);
            }
        } catch (SQLException e){
            System.out.println("Erro ao buscar Matérias: " + e.getMessage());
        } finally {
            try {
                if(rs != null) rs.close();
                if(pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }



        return materias;
    }

    public boolean updateMaterias(String materia, int id){
        connectToDb();
        String sql = "UPDATE materia SET materia=? WHERE id=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, materia);
            pst.setInt(2, id);
            pst.execute();

            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao Atualizar o Materia: " + e.getMessage());
            return false;

        } finally {
            try {
                if(pst != null) pst.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

    public boolean deleteMaterias(int id) {
        connectToDb();
        String sql = "DELETE FROM materia WHERE id=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar Matéria: " + e.getMessage());
            return false;
        } finally {
            try {
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

    public boolean insertMaterias(int id_vestibular, String materia){
        connectToDb();
        String sql = "INSERT INTO materia (id_vestibular, materia) VALUES (?, ?)";


        try {

            pst = connection.prepareStatement(sql);
            pst.setInt(1, id_vestibular);
            pst.setString(2, materia);
            pst.execute();

            return true;

        } catch (SQLException e) {

            System.out.println("Erro ao Inserir o Materia: " + e.getMessage());
            return false;

        } finally {
            try {
                if(pst != null) pst.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

    public List<Materia> selectTodasAsMaterias() {
        List<Materia> materias = new ArrayList<>();
        connectToDb();
        String sql = "SELECT * FROM materia";

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
                Materia materia = new Materia(
                        rs.getInt("id"),
                        rs.getString("materia")
                );

                materias.add(materia);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar materias: " + e.getMessage());
        } finally {
            try {
                if(rs != null) rs.close();
                if (st != null) st.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return materias;
    }
}