package ajude.psoft.projeto.entidades;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Classe que possui uma representação mais simples de uma doação, serve como Body que são passados
 * como request no controlador de doações.
 * 
 * @author Mauricio Marques da Silva Monte e Natan Ataide de Souza.
 */
public class DoacaoDTO {

    private String emailDono;
    private long idCampanha;
    private float valorDoado;
    @Temporal(TemporalType.DATE)
    private Date dataPostagem;

    /**
    * Constrói a DoacaoDTO a partir dos dados passados como parâmetro.
	* 
	* @param emailDono email do usuário doador
    * @param idCampanha id da campanha que receberá a doação
    * @param valorDoado valor que o usuário doou
	* @param dataPostagem data da realização da doação
	*/
    public DoacaoDTO(String emailDono, long idCampanha, float valorDoado, Date dataPostagem) {
        this.emailDono = emailDono;
        this.idCampanha = idCampanha;
        this.valorDoado = valorDoado;
        this.dataPostagem = dataPostagem;
    }

    /**
    * Método responsável por fazer a transformação de uma DoacaoDTO para uma Doacao completa.
	* 
	* @return Doacao doação fruto da transformação
	*/
    public Doacao transformaParaDoacao(){
        Doacao doacao = new Doacao();
        doacao.setDataPostagem(dataPostagem);
        doacao.setValorDoado(valorDoado);
        return doacao;
    }

    /**
    * getters e setters.
    */

    public String getEmailDono() {
        return this.emailDono;
    }

    public void setEmailDono(String emailDono) {
        this.emailDono = emailDono;
    }

    public long getIdCampanha() {
        return this.idCampanha;
    }

    public void setIdCampanha(long idCampanha) {
        this.idCampanha = idCampanha;
    }

    public float getValorDoado() {
        return this.valorDoado;
    }

    public void setValorDoado(float valorDoado) {
        this.valorDoado = valorDoado;
    }

    public Date getDataPostagem() {
        return this.dataPostagem;
    }

    public void setDataPostagem(Date dataPostagem) {
        this.dataPostagem = dataPostagem;
    }

}