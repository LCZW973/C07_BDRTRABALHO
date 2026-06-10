package br.faculdade.dao;

import br.faculdade.models.usuarios.Vestibulando;

import java.sql.SQLException;

public class VestibulandoDAO extends ConnectionDao{

    public boolean insertVestibulando(Vestibulando vestibulando){
        connectToDb();
        String sql = "INSERT INTO vestibulando (cpf, nome, email, numero_sala) VALUES (?, ?, ?, ?)";


        try {

            pst = connection.prepareStatement(sql);
            pst.setString(1, vestibulando.getCpf());
            pst.setString(2, vestibulando.getNome());
            pst.setString(3, vestibulando.getEmail());
            pst.setInt(4, vestibulando.getSala().getNumeroSala());
            pst.execute();

            return true;

        } catch (SQLException e) {

            System.out.println("Erro ao Inserir o Vestibulando: " + e.getMessage());
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



    public Vestibulando selectVestibulando(String cpf){
        Vestibulando vestibulando = null;
        connectToDb();
        SalaDAO salaDAO = new SalaDAO();
        String sql = "SELECT * FROM vestibulando WHERE cpf=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, cpf);
            rs = pst.executeQuery();
            if(rs.next()) {
                vestibulando = new Vestibulando(
                        rs.getString("cpf"),
                        rs.getString("nome"),
                        rs.getString("email")
                );

                vestibulando.setSala(salaDAO.selectSala(rs.getInt("numero_sala")));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao Selecionar o Vestibulando: " + e.getMessage());

        } finally {
            try {
                if(rs != null) rs.close();
                if(pst != null) pst.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return vestibulando;
    }



    public boolean updateVestibulando(Vestibulando vestibulando){
        connectToDb();
        String sql = "UPDATE vestibulando SET email=? WHERE cpf=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, vestibulando.getEmail());
            pst.setString(2, vestibulando.getCpf());
            pst.execute();

            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao Atualizar o Vestibulando: " + e.getMessage());
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

    public boolean deleteVestibulando(String cpf) {
        connectToDb();
        String sql = "DELETE FROM vestibulando WHERE cpf=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, cpf);
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar Vestibulando: " + e.getMessage());
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
