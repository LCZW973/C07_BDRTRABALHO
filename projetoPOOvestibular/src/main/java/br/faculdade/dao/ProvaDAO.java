package br.faculdade.dao;

import br.faculdade.models.curso.vestibular.Prova;
import br.faculdade.models.curso.vestibular.Resultado;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProvaDAO extends ConnectionDao{

    public boolean insertProva(int edicao, String cpf){
        connectToDb();
        String sql = "INSERT INTO prova (edicao, cpf_corretor) VALUES (?, ?)";


        try {

            pst = connection.prepareStatement(sql);
            pst.setInt(1, edicao);
            pst.setString(2, cpf);
            pst.execute();

            return true;

        } catch (SQLException e) {

            System.out.println("Erro ao Inserir o Prova: " + e.getMessage());
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



    public List<Prova> selectProva(String cpf){
        List<Prova> provas = new ArrayList<>();
        connectToDb();
        String sql = "SELECT * FROM prova INNER JOIN vestibulando_realiza_prova " +
                "ON vestibulando_realiza_prova.id_prova = prova.id WHERE vestibulando_realiza_prova.cpf_vestibulando=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, cpf);
            rs = pst.executeQuery();
            while(rs.next()) {
                Prova prova = new Prova(
                        rs.getInt("edicao"),
                        rs.getString("cpf_corretor")
                );

                prova.setId(rs.getInt("id"));

                prova.setResultado(new Resultado(rs.getFloat("nota")));

                provas.add(prova);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao Selecionar o Prova: " + e.getMessage());

        } finally {
            try {
                if(rs != null) rs.close();
                if(pst != null) pst.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return provas;
    }



    public boolean updateProva(Prova prova){
        connectToDb();
        String sql = "UPDATE prova SET edicao=? WHERE id=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, prova.getEdicao());
            pst.setInt(2, prova.getId());
            pst.execute();

            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao Atualizar o Prova: " + e.getMessage());
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

    public boolean deleteProva(Prova prova) {
        connectToDb();
        String sql = "DELETE FROM prova WHERE id=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, prova.getId());
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar Prova: " + e.getMessage());
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
}
