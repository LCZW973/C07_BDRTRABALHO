package br.faculdade.dao;

import br.faculdade.models.curso.vestibular.Vestibular;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VestibularDAO extends ConnectionDao{

    public Vestibular selectVestibular(int id){
        connectToDb();
        Vestibular vestibular = null;
        String sql = "SELECT * FROM vestibular WHERE id=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if(rs.next()) {
                vestibular = new Vestibular(
                        rs.getInt("id"),
                        rs.getString("data_realizacao")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao Selecionar o Vestibular: " + e.getMessage());

        } finally {
            try {
                if(rs != null) rs.close();
                if(pst != null) pst.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return vestibular;
    }

    public boolean updateVestibulares(String data, int id){
        connectToDb();
        String sql = "UPDATE vestibular SET data_realizacao=? WHERE id=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, data);
            pst.setInt(2, id);
            pst.execute();

            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao Atualizar o Vestibular: " + e.getMessage());
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

    public boolean deleteVestibular(int id) {
        connectToDb();
        String sql = "DELETE FROM vestibular WHERE id=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar Vestibular: " + e.getMessage());
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

    public boolean insertVestibular(String localizacao, String data){
        connectToDb();
        String sql = "INSERT INTO vestibular (localizacao, data_realizacao) VALUES (?, ?)";


        try {

            pst = connection.prepareStatement(sql);
            pst.setString(1, localizacao);
            pst.setString(2, data);
            pst.execute();

            return true;

        } catch (SQLException e) {

            System.out.println("Erro ao Inserir o Vestibular: " + e.getMessage());
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

    public List<Vestibular> selectVestibulares() {
        List<Vestibular> vestibulares = new ArrayList<>();
        connectToDb();
        String sql = "SELECT * FROM vestibular";

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
                Vestibular vestibular = new Vestibular(
                        rs.getInt("id"),
                        rs.getString("data_realizacao")
                );

                vestibulares.add(vestibular);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar vestibulares: " + e.getMessage());
        } finally {
            try {
                if(rs != null) rs.close();
                if (st != null) st.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return vestibulares;
    }
}
