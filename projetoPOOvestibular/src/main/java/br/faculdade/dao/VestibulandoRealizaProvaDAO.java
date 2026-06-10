package br.faculdade.dao;

import br.faculdade.models.curso.vestibular.Resultado;

import java.sql.SQLException;

public class VestibulandoRealizaProvaDAO extends ConnectionDao{

    public boolean insertRelacaoVestibulandoEProva(String cpf_vestibulando, int id_prova){
        connectToDb();
        String sql = "INSERT INTO vestibulando_realiza_prova (cpf_vestibulando, id_prova) VALUES (?, ?)";


        try {

            pst = connection.prepareStatement(sql);
            pst.setString(1, cpf_vestibulando);
            pst.setInt(2, id_prova);
            pst.execute();

            return true;

        } catch (SQLException e) {

            System.out.println("Erro ao Inserir Relação de Vestibulando e Prova: " + e.getMessage());
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

    public boolean lancarNota(String cpf_vestibulando, int id_prova, int nota){
        connectToDb();
        String sql = "UPDATE vestibulando_realiza_prova " +
                "SET nota=? WHERE cpf_vestibulando=? AND id_prova=?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, nota);
            pst.setString(2, cpf_vestibulando);
            pst.setInt(3, id_prova);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao lançar a nota: " + e.getMessage());
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

    // le a nota do vestibulando num vestibular (null = ainda nao corrigida)
    public Resultado selectNota(String cpf_vestibulando, int id_prova){
        connectToDb();
        String sql = "SELECT nota FROM vestibulando_realiza_prova " +
                "WHERE cpf_vestibulando=? AND id_prova=?";
        Resultado resultado = null;
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, cpf_vestibulando);
            pst.setInt(2, id_prova);
            rs = pst.executeQuery();
            if (rs.next()) {
                float nota = rs.getFloat("nota");
                // wasNull() distingue "nota NULL" (nao corrigida) de "nota 0"
                resultado = new Resultado(rs.wasNull() ? null : nota);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar a nota: " + e.getMessage());
        } finally {
            try {
                if(rs != null) rs.close();
                if(pst != null) pst.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return resultado;
    }
}
