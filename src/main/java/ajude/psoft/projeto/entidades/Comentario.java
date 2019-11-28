package ajude.psoft.projeto.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe que tem a função de ser a entidade comentário que armazena dados do usuário, da campanha
 * e dos comentários em sí.
 * 
 * @author Mauricio Marques da Silva Monte e Natan Ataide de Souza.
 */
@Entity
public class Comentario {

    @Id
    @GeneratedValue
    private long id;
    private String texto;
    @ManyToOne
    private Usuario usuario;
    @OneToMany
    private List<Comentario> respostas;
    @Temporal(TemporalType.DATE)
    private Date dataPostagem;
    @JsonIgnore
    @ManyToOne
    private Campanha campanha;

    /**
    * Constrói um comentário vazio.
	*/
    public Comentario() {
        super();
    }

    /**
    * Constrói um comentário a partir dos dados passados como parâmetro, incluido o id do comentário.
	* 
	* @param id identificador único do comentário
    * @param texto texto do comentário
    * @param usuario usuário que postou o comentário
    * @param respostas resposta que um comentário pode possuir
    * @param dataPostagem data da postagem do comentário
    * @param campanha campanha onde o comentário foi postado
	*/
    public Comentario(long id, String texto, Usuario usuario, ArrayList<Comentario> respostas, Date dataPostagem, Campanha campanha){
        super();
        this.id = id;
        this.texto = texto;
        this.usuario = usuario;
        this.respostas = respostas;
        this.dataPostagem = dataPostagem;
        this.campanha = campanha;
    }

    /**
    * Constrói um comentário a partir dos dados passados como parâmetro.
	* 
    * @param texto texto do comentário
    * @param usuario usuário que postou o comentário
    * @param respostas resposta que um comentário pode possuir
    * @param dataPostagem data da postagem do comentário
    * @param campanha campanha onde o comentário foi postado
	*/
    public Comentario(String texto, Usuario usuario, ArrayList<Comentario> respostas, Date dataPostagem, Campanha campanha){
        super();
        this.texto = texto;
        this.usuario = usuario;
        this.respostas = respostas;
        this.dataPostagem = dataPostagem;
        this.campanha = campanha;
    }

    /**
	* Método responsável por adicionar uma resposta em um comentário.
    * 
    * @param resposta resposta a ser adicionada
	*/
    public void adicionarResposta(Comentario resposta){
        this.respostas.add(resposta);
    }

    /**
	* Método responsável por "remover" uma resposta de um comentário.
	*/
    public void removerResposta(){
        this.setTexto("");
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

    public String getTexto() {
        return this.texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Comentario> getRespostas() {
        return this.respostas;
    }

    public void setRespostas(List<Comentario> respostas) {
        this.respostas = respostas;
    }

    public Date getDataPostagem() {
        return this.dataPostagem;
    }

    public void setDataPostagem(Date dataPostagem) {
        this.dataPostagem = dataPostagem;
    }

    public Campanha getCampanha() {
        return this.campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }

}
