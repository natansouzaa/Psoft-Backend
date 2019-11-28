package ajude.psoft.projeto.ordenacoes;

import ajude.psoft.projeto.entidades.Campanha;

/**
 * Classe responsável por comparar Campanhas pela quantidade de curtidas.
 * 
 * @author Mauricio Marques da Silva Monte e Natan Ataide de Souza.
 **/
public class OrdenaPelaCurtida implements Comparadores {

    /**
	 * Metodo que sobrescreve o compare do Comparator e compara dois
	 * objetos do tipo Campanha pela quantidade de curtidas.
	 * 
	 * @param o1 o item a ser comparado
	 * @param o2 o item a ser comparado
	 */
    @Override
    public int compare(Object o1, Object o2) {
        Campanha campanha1 = (Campanha) o1;
        Campanha campanha2 = (Campanha) o2;
        
        if (campanha1.getCurtidas().size() > campanha2.getCurtidas().size()){
            return -1;
        }
        if (campanha1.getCurtidas().size() < campanha2.getCurtidas().size()){
            return 1;
        }
        return 0;
    }

}