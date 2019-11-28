package ajude.psoft.projeto.entidades;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Classe que possui uma representação mais simples de um comentário, serve como Body que são passados
 * como request no controlador de comentários.
 * 
 * @author Mauricio Marques da Silva Monte e Natan Ataide de Souza.
 */
public class ComentarioDTO {

    private String texto;
    @Temporal(TemporalType.DATE)
    private Date dataPostagem;
    private long id;

    /**
    * Constrói a ComentarioDTO a partir dos dados passados como parâmetro.
	* 
	* @param texto texto do comentário
    * @param dataPostagem data da postagem do comentário
    * @param id identificador único da campanha ou do comentário onde será comentado
	*/
    public ComentarioDTO(String texto, Date dataPostagem, Long id) {
        this.texto = texto;
        this.dataPostagem = dataPostagem;
        this.id = id;
    }

    /**
    * Método responsável por fazer a transformação de um ComentarioDTO para um Comentario completo.
	* 
	* @return Comantario comentario fruto da transformação
	*/
    public Comentario transformaParaComentario(){
        Comentario retorno = new Comentario();
        retorno.setTexto(this.texto);
        retorno.setDataPostagem(this.dataPostagem);
        retorno.setRespostas(new ArrayList<>());
        return retorno;
    }

    /**
    * getters e setters.
    */

    public String getTexto() {
        return this.texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getDataPostagem() {
        return this.dataPostagem;
    }

    public void setDataPostagem(Date dataPostagem) {
        this.dataPostagem = dataPostagem;
    }

    public long getId() {
        return this.id;
    }

    public void setIdCampanha(long id) {
        this.id = id;
    }

}
