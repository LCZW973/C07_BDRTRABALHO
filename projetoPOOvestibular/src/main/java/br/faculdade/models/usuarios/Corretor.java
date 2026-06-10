package br.faculdade.models.usuarios;
import br.faculdade.dao.VestibulandoRealizaProvaDAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Corretor extends Pessoa {

    public Corretor(String cpf, String nome, String email) {
        super(cpf, nome, email);
    }

    @Override
    public void confereSeusDadosEspecificos() {
        System.out.println("Funcao : Corretor");
    }

    @Override
    public void atualizaEmail(String novoEmail) {
        this.email = novoEmail;
    }

    // lanca as notas lendo de um arquivo (cpf ; id_vestibular ; nota)
    // cada nota e lancada por uma thread (Runnable) -> lote I/O-bound em paralelo
    public void lancarNotasPorArquivo(String caminhoArquivo) {

        // 1) le o arquivo (sequencial)
        List<String[]> registros = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.isBlank()) continue;
                String[] campos = linha.split(";");           // cpf ; id_vestibular ; nota
                if (campos.length < 3) {
                    System.out.println("Linha invalida (esperado cpf;id_vestibular;nota): " + linha);
                    continue;
                }
                registros.add(campos);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        // 2) cria uma thread (Runnable) por nota e inicia todas
        List<Thread> threads = new ArrayList<>();
        for (String[] campos : registros) {
            Thread t = new Thread(new LancadorDeNota(campos));
            t.start();
            threads.add(t);
        }

        // 3) espera todas as threads terminarem (join)
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Lancamento interrompido.");
            }
        }

        System.out.println("Lancamento de notas finalizado.");
    }

    // classe que implementa Runnable: a tarefa de lancar UMA nota
    private static class LancadorDeNota implements Runnable {
        private final String[] campos;

        public LancadorDeNota(String[] campos) {
            this.campos = campos;
        }

        @Override
        public void run() {
            try {
                String cpf = campos[0].trim();
                int idProva = Integer.parseInt(campos[1].trim());
                int nota = Integer.parseInt(campos[2].trim());

                // cada thread usa o PROPRIO DAO (conexao propria) -> thread-safe
                VestibulandoRealizaProvaDAO dao = new VestibulandoRealizaProvaDAO();
                if (dao.lancarNota(cpf, idProva, nota))
                    System.out.println("Nota " + nota + " lancada para " + cpf);
                else
                    System.out.println("Falha ao lancar nota de " + cpf);
            } catch (NumberFormatException e) {
                System.out.println("Linha com numero invalido: " + e.getMessage());
            }
        }
    }
}