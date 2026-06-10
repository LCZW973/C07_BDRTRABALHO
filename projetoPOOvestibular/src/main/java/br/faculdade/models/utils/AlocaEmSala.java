package br.faculdade.models.utils;

import br.faculdade.models.curso.vestibular.Sala;
import br.faculdade.dao.SalaDAO;
import br.faculdade.dao.VestibulandoDAO;
import br.faculdade.models.usuarios.Vestibulando;

import java.util.List;

public class AlocaEmSala {

    public static boolean alocaEmSala(Vestibulando vestibulando){
        SalaDAO salaDAO = new SalaDAO();
        VestibulandoDAO vestibulandoDAO = new VestibulandoDAO();

        List<Sala> salas = salaDAO.selectTodasAsSalas(); // Armazena todas as salas disponíveis

        // Armazena se foi ou não possível alocar o vestibulando em alguma sala, começa como falso, pois vestibulando não começa alocado em nenhuma sala
        boolean alocado = false;

        for(int i = 1; i <= salas.size(); i++){
            vestibulando.setSala(salas.get(i-1));
            if(vestibulandoDAO.insertVestibulando(vestibulando)) {
                alocado = true;
                break;
            }
            System.out.println("Realocando para próxima sala.");
        }

        return alocado;
    }
}
