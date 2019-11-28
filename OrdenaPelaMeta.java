package ajude.psoft.projeto.ordenacoes;

import ajude.psoft.projeto.entidades.Campanha;

/**
 * Classe responsável por comparar Campanhas pela meta que está mais próxima.
 * 
 * @author Mauricio Marques da Silva Monte e Natan Ataide de Souza.
 **/
public class OrdenaPelaMeta implements Comparadores {

    /**
	 * Metodo que sobrescreve o compare do Comparator e compara dois
	 * objetos do tipo Campanha pela meta que está mais próxima.
	 * 
	 * @param o1 o item a ser comparado
	 * @param o2 o item a ser comparado
	 */
    @Override
    public int compare(Object o1, Object o2) {
        Campanha campanha1 = (Campanha) o1;
        Campanha campanha2 = (Campanha) o2;

        float quantiaRestanteCampanha1 = campanha1.getMeta() - campanha1.getArrecadado();
        float quantiaRestanteCampanha2 = campanha2.getMeta() - campanha2.getArrecadado();

        if (quantiaRestanteCampanha1 > quantiaRestanteCampanha2){
            return 1;
        }
        if (quantiaRestanteCampanha1 < quantiaRestanteCampanha2){
            return -1;
        }
        return 0;
    }

}