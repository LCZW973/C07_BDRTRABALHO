package br.faculdade.dao;

import br.faculdade.models.usuarios.Corretor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CorretorDAO extends ConnectionDao{
    public boolean InserirCorretor(Corretor corretor) {
        connectToDb();
        String sql = "INSERT INTO corretor (cpf, nome, email) VALUES (?, ?, ?)";
        boolean sucesso = false;

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, corretor.getCpf());
            pst.setString(2, corretor.getNome());
            pst.setString(3, corretor.getEmail());

            int linhas = pst.executeUpdate();   // executeUpdate pra INSERT/UPDATE/DELETE
            sucesso = linhas > 0;                // true se inseriu de fato
        } catch (SQLException e) {
            System.out.println("Erro ao inserir corretor : " + e.getMessage());
        } finally {
            try {
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return sucesso;
    }

    public Corretor autentica(String cpf, String email) {
        connectToDb();
        String sql = "SELECT cpf, nome, email FROM corretor WHERE cpf = ? AND email = ?";
        Corretor corretor = null ;
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, cpf);
            pst.setString(2, email);
            rs = pst.executeQuery();
            if (rs.next()) {
                // corretor nao tem materia: a nota geral vai direto em vestibulando_presta_vestibular
                corretor = new Corretor(rs.getString("cpf"), rs.getString("nome"),
                        rs.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar corretor : " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return corretor; // null = nao autenticado
    }

    // atualiza dados do corretor no BD (usado ao trocar e-mail)
    public boolean updateCorretor(Corretor corretor){
        connectToDb();
        String sql = "UPDATE corretor SET nome=?, email=? WHERE cpf=?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, corretor.getNome());
            pst.setString(2, corretor.getEmail());
            pst.setString(3, corretor.getCpf());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar corretor : " + e.getMessage());
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

    public String cpfCorretorComMenosProvas() {
        connectToDb();
        String cpf = null;
        String sql = "SELECT cpf FROM corretor\n" +
                "LEFT JOIN prova AS p ON p.cpf_corretor = corretor.cpf\n" +
                "GROUP BY corretor.cpf\n" +
                "ORDER BY COUNT(p.id) ASC\n" +
                "LIMIT 1;";
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) {
                cpf = rs.getString("cpf");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar corretor : " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return cpf;
    }

    public boolean deleteCorretor(String cpf) {
        connectToDb();
        String sql = "DELETE FROM corretor WHERE cpf=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, cpf);
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar Corretor: " + e.getMessage());
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

    public List<Corretor> selectCorretores() {
        List<Corretor> corretores = new ArrayList<>();
        connectToDb();
        String sql = "SELECT * FROM corretor";

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
                Corretor corretor = new Corretor(
                        rs.getString("cpf"),
                        rs.getString("nome"),
                        rs.getString("email")
                );

                corretores.add(corretor);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar corretores: " + e.getMessage());
        } finally {
            try {
                if(rs != null) rs.close();
                if (st != null) st.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return corretores;
    }
}