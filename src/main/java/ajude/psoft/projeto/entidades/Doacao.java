package ajude.psoft.projeto.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe que tem a função de ser a entidade doação que armazena dados do usuário, da campanha
 * e das doações em sí.
 * 
 * @author Mauricio Marques da Silva Monte e Natan Ataide de Souza.
 */
@Entity
public class Doacao {

    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private Usuario usuario;
    private float valorDoado;
    @JsonIgnore
    @ManyToOne
    private Campanha campanha;
    @Temporal(TemporalType.DATE)
    private Date dataPostagem;

    /**
    * Constrói uma doação vazia.
	*/
    public Doacao(){
        super();
    }

    /**
    * Constrói uma doação a partir dos dados passados como parâmetro, incluido o id da doação.
	* 
	* @param id identificador único da doação
    * @param usuario usuário que realizou a doação
    * @param valorDoado valor que o usuário doou
    * @param campanha campanha que o usuário ajudou
    * @param dataPostagem data de realização da doação
	*/
    public Doacao(long id, Usuario usuario, float valorDoado, Campanha campanha, Date dataPostagem) {
        super();
        this.id = id;
        this.usuario = usuario;
        this.valorDoado = valorDoado;
        this.campanha = campanha;
        this.dataPostagem = dataPostagem;
    }

    /**
    * Constrói uma doação a partir dos dados passados como parâmetro.
	* 
    * @param usuario usuário que realizou a doação
    * @param valorDoado valor que o usuário doou
    * @param campanha campanha que o usuário ajudou
    * @param dataPostagem data de realização da doação
	*/
    public Doacao(Usuario usuario, float valorDoado, Campanha campanha, Date dataPostagem) {
        super();
        this.usuario = usuario;
        this.valorDoado = valorDoado;
        this.campanha = campanha;
        this.dataPostagem = dataPostagem;
    }

    /**
    * getters e setters.
    */

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public float getValorDoado() {
        return this.valorDoado;
    }

    public void setValorDoado(float valorDoado) {
        this.valorDoado = valorDoado;
    }

    public Campanha getCampanha() {
        return this.campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }

    public Date getDataPostagem() {
        return this.dataPostagem;
    }

    public void setDataPostagem(Date dataPostagem) {
        this.dataPostagem = dataPostagem;
    }

}