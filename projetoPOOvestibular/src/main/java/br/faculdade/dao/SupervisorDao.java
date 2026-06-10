package br.faculdade.dao;
import br.faculdade.models.usuarios.Supervisor;
import br.faculdade.models.usuarios.Vestibulando;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupervisorDao extends ConnectionDao {

    public Supervisor autentica(String cpf, String email) {
        connectToDb();
        String sql = "SELECT cpf, nome, email FROM supervisor WHERE cpf = ? AND email = ?";
        Supervisor supervisor = null;

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, cpf);
            pst.setString(2,email);
            rs = pst.executeQuery();
            if (rs.next())
            {
                System.out.println("Dado encontrado");
                supervisor = new Supervisor(rs.getString("cpf"),rs.getString("nome"),rs.getString("email"),0);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar supervisor : " + e.getMessage());
        } finally {

            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("erro ao fechar recursos" + e.getMessage());
            }
        }


        return supervisor ;
    }

    public boolean updateSupervisor(Supervisor supervisor) {
        connectToDb();

        String sql = "UPDATE supervisor SET  email=? WHERE cpf=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, supervisor.getEmail());
            pst.setString(2, supervisor.getCpf());
            pst.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar supervisor: " + e.getMessage());
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

    public List<Vestibulando>  selectCpfVestibulando(Supervisor supervisor){

        List<Vestibulando> listVestibulando = new ArrayList<Vestibulando>();
        connectToDb();
        String sql = "select vestibulando.* from supervisor join sala on supervisor.cpf = sala.cpf_supervisor join vestibulando on sala.numero = vestibulando.numero_sala where supervisor.cpf = ?";


        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, supervisor.getCpf());
            rs = pst.executeQuery();
            while(rs.next())
            {
                Vestibulando vestibulando = new Vestibulando(
                        rs.getString("cpf"),
                        rs.getString("nome"),
                        rs.getString("email")

                );
                listVestibulando.add((vestibulando));
            };
        } catch (SQLException e) {
            System.out.println("Erro ao buscar os CPFs dos vestibulandos: " + e.getMessage());

        } finally {
            try {
                if(rs != null) rs.close();
                if(pst != null) pst.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return  listVestibulando;
    }

    public boolean deleteSupervisor(String cpf) {
        connectToDb();
        String sql = "DELETE FROM supervisor WHERE cpf=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, cpf);
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar Supervisor: " + e.getMessage());
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

    public boolean insertSupervisor(String cpf, String nome, String email){
        connectToDb();
        String sql = "INSERT INTO supervisor (cpf, nome, email) VALUES (?, ?, ?)";


        try {

            pst = connection.prepareStatement(sql);
            pst.setString(1, cpf);
            pst.setString(2, nome);
            pst.setString(3, email);
            pst.execute();

            return true;

        } catch (SQLException e) {

            System.out.println("Erro ao Inserir o Supervisor: " + e.getMessage());
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

    public List<Supervisor> selectSupervisores() {
        List<Supervisor> supervisores = new ArrayList<>();
        connectToDb();
        String sql = "SELECT * FROM supervisor";

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
                Supervisor supervisor = new Supervisor(
                        rs.getString("cpf"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        1
                );

                supervisores.add(supervisor);
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

        return supervisores;
    }
}