package ajude.psoft.projeto.entidades;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Classe que possui uma representação mais simples de uma campanha, serve como Body que são passados
 * como request no controlador de campanhas.
 * 
 * @author Mauricio Marques da Silva Monte e Natan Ataide de Souza.
 */
public class CampanhaDTO {

    private String nomeCurto;
    private String identificadorURL;
    private String descricao;
    @Temporal(TemporalType.DATE)
    private Date dataLimite;
    private float meta;
    private String emailDono;   

    /**
    * Constrói a CampanhaDTO a partir dos dados passados como parâmetro.
	* 
	* @param nomeCurto nome curto da campanha
    * @param identificadorURL URL da campanha
    * @param descricao descrição da campanha
    * @param dataLimite data limite para arrecadação do dinheiro da campanha
    * @param meta meta de dinheiro que a campanha necessita
	* @param emailDono email do dono da campanha
	*/
    public CampanhaDTO(String nomeCurto, String identificadorURL, String descricao, Date dataLimite, float meta, String emailDono) {
        this.nomeCurto = nomeCurto;
        this.identificadorURL = identificadorURL;
        this.descricao = descricao;
        this.dataLimite = dataLimite;
        this.meta = meta;
        this.emailDono = emailDono;
    }

    /**
    * Método responsável por fazer a transformação de uma CampanhaDTO para uma Campanha completa.
	* 
	* @return Campanha campanha fruto da transformação
	*/
    public Campanha transformarParaCampanha(){
        Campanha retorno = new Campanha();
        retorno.setDataLimite(this.getDataLimite());
        retorno.setDescricao(this.getDescricao());
        retorno.setCurtidas(new ArrayList<Curtida>());
        retorno.setIdentificadorURL(this.getIdentificadorURL());
        retorno.setMeta(this.getMeta());
        retorno.setNomeCurto(this.getNomeCurto());
        retorno.setStatus(Estado.ATIVA);
        retorno.setArrecadado(0);
        return retorno;
    }

    /**
    * getters e setters.
    */

    public String getNomeCurto() {
        return this.nomeCurto;
    }

    public void setNomeCurto(String nomeCurto) {
        this.nomeCurto = nomeCurto;
    }

    public String getIdentificadorURL() {
        return this.identificadorURL;
    }

    public void setIdentificadorURL(String identificadorURL) {
        this.identificadorURL = identificadorURL;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataLimite() {
        return this.dataLimite;
    }

    public void setDataLimite(Date dataLimite) {
        this.dataLimite = dataLimite;
    }

    public float getMeta() {
        return this.meta;
    }

    public void setMeta(float meta) {
        this.meta = meta;
    }

    public String getEmailDono() {
        return this.emailDono;
    }

    public void setEmailDono(String emailDono) {
        this.emailDono = emailDono;
    }

}