package br.faculdade.models.utils.telas.diretor.privilegiosEm;

import br.faculdade.dao.VestibularDAO;
import br.faculdade.models.curso.vestibular.Vestibular;

import static br.faculdade.Main.sc;

public class PrivilegiosDoDiretorSobreVestibular {

    public static void privilegiosDoDiretorSobreVestibular() {
        System.out.println("O que deseja fazer com o Vestibular:" +
                "\n1 - Alterar Data de Realização | 2 - Deletar um Vestibular" +
                "\n3 - Inserir Vestibular         | 4 - Mostrar Vestibulares");

        int opcao = sc.nextInt();

        VestibularDAO vestibularDAO = new VestibularDAO();
        if(opcao == 1) {
            System.out.println("Insira o id do vestibular: ");
            int id = sc.nextInt();

            System.out.println("Insira o dia: ");
            int dia = sc.nextInt();
            System.out.println("Insira o mês: ");
            int mes = sc.nextInt();
            System.out.println("Insira o ano: ");
            int ano = sc.nextInt();

            String data = ano + "-" + mes + "-" + dia;
            if(vestibularDAO.updateVestibulares(data, id)) {
                System.out.println("Data alterada com sucesso!");
            }

        } else if (opcao == 2) {
            System.out.println("Insira o ID do vestibular que deseja deletar: ");
            int id = sc.nextInt();
            if (vestibularDAO.deleteVestibular(id)) {
                System.out.println("Vestibular deletado com sucesso!");
            }

        } else if(opcao == 3) {
            System.out.println("Insira a localização do Vestibular: ");
            sc.nextLine();
            String localizacao = sc.nextLine();
            System.out.print("Insira a data do Vestibular. ");
            System.out.println("Insira o dia: ");
            int dia = sc.nextInt();
            System.out.println("Insira o mês: ");
            int mes = sc.nextInt();
            System.out.println("Insira o ano: ");
            int ano = sc.nextInt();
            String data = ano + "-" + mes + "-" + dia;

            if(vestibularDAO.insertVestibular(localizacao, data)){
                System.out.println("Vestibular adicionado com sucesso!");
            }

        } else if (opcao == 4) {

            for(Vestibular vestibular : vestibularDAO.selectVestibulares()) {
                System.out.println("ID: " + vestibular.getId() + "; Data: " + vestibular.getData_vestibular());
            }

        } else {
            System.out.println("Não há essa opção.");
        }
    }
}
