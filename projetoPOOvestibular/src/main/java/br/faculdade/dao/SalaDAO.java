package br.faculdade.dao;

import br.faculdade.models.curso.vestibular.Sala;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaDAO extends ConnectionDao{

    public boolean insertSala(Sala sala){
        connectToDb();
        String sql = "INSERT INTO sala VALUES (?, ?, ?)";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, sala.getNumeroSala());
            pst.setInt(2, sala.getCapacidadeSala());
            pst.setString(3, sala.getCpf_supervisor());
            pst.execute();

            return true;

        } catch (SQLException e) {

            System.out.println("Erro ao Inserir a Sala : " + e.getMessage());
            return false;

        } finally {
            try {
                if(rs != null) rs.close();
                if(st != null) st.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos" + e.getMessage());
            }
        }
    }



    public Sala selectSala(int numero_sala){
        Sala sala = null;
        connectToDb();
        String sql = "SELECT * FROM sala WHERE numero=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, numero_sala);
            rs = pst.executeQuery();
            if(rs.next()) {
                sala = new Sala(
                        rs.getInt("numero"),
                        rs.getInt("vagas"),
                        rs.getString("cpf_supervisor")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao Selecionar a Sala:" + e.getMessage());

        } finally {
            try {
                if(rs != null) rs.close();
                if(pst != null) pst.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos" + e.getMessage());
            }
        }

        return sala;
    }



    public List<Sala> selectTodasAsSalas(){
        List<Sala> salas = new ArrayList<>();
        connectToDb();
        String sql = "SELECT * FROM sala";

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
                Sala sala = new Sala(
                        rs.getInt("numero"),
                        rs.getInt("vagas"),
                        rs.getString("cpf_supervisor")
                );

                if(sala.getCpf_supervisor() == null) {
                    sala.setCpf_supervisor("SEM SUPERVISOR ALOCADO");
                }

                salas.add(sala);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao Selecionar as Salas:" + e.getMessage());

        } finally {
            try {
                if(rs != null) rs.close();
                if(st != null) st.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos" + e.getMessage());
            }
        }

        return salas;
    }

    public Sala selectSalaSupervisor(String cpf){
        Sala sala = null;
        connectToDb();
        String sql = "SELECT * FROM sala WHERE cpf_supervisor=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, cpf);
            rs = pst.executeQuery();
            if(rs.next()) {
                sala = new Sala(
                        rs.getInt("numero"),
                        rs.getInt("vagas"),
                        rs.getString("cpf_supervisor")

                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao Selecionar a Sala:" + e.getMessage());

        } finally {
            try {
                if(rs != null) rs.close();
                if(pst != null) pst.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos" + e.getMessage());
            }
        }

        return sala;
    }

    public boolean updateSala(int vagas, int numero){
        connectToDb();
        String sql = "UPDATE sala SET vagas=? WHERE numero=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, vagas);
            pst.setInt(2, numero);
            pst.execute();

            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao Atualizar o Sala: " + e.getMessage());
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

    public boolean deleteSala(int numero) {
        connectToDb();
        String sql = "DELETE FROM sala WHERE numero=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, numero);
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar Sala: " + e.getMessage());
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

    public boolean updateSalaAlocandoUmNovoSupervisor(String cpf, int numero){
        connectToDb();
        String sql = "UPDATE sala SET cpf_supervisor=? WHERE numero=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, cpf);
            pst.setInt(2, numero);
            pst.execute();

            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao Atualizar o Sala: " + e.getMessage());
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
}