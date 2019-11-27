package ajude.psoft.projeto.ordenacoes;

import ajude.psoft.projeto.entidades.Campanha;

public class OrdenaPelaCurtida implements Comparadores {

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