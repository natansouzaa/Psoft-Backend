package ajude.psoft.projeto.entidades;

/**
 * Classe que possui uma representação mais simples de uma camnpanha, possui somente o identificadorURL
 * e a novaDescricao que a campanha reveberá. Serve somente para editar as campanhas que já existem no banco de dados.
 * 
 * @author Mauricio Marques da Silva Monte e Natan Ataide de Souza.
 */
public class EditaCampanha {

    private String identificadorURL;
    private String novaDescricao;

    /**
    * Constrói a entidade EditaCampanha a partir dos dados passados como parâmetro.
	* 
	* @param identificadorURL identificadorURL da campanha 
	* @param novaDescricao nova descricao que será atribuida a campanha
	*/
    public EditaCampanha(String identificadorURL, String novaDescricao){
        this.identificadorURL = identificadorURL;
        this.novaDescricao = novaDescricao;
    }

    /**
    * getters e setters.
    */
    
    public String getIdentificadorURL() {
        return this.identificadorURL;
    }

    public void setIdentificadorURL(String identificadorURL) {
        this.identificadorURL = identificadorURL;
    }

    public String getNovaDescricao() {
        return this.novaDescricao;
    }

    public void setNovaDescricao(String novaDescricao) {
        this.novaDescricao = novaDescricao;
    }

}