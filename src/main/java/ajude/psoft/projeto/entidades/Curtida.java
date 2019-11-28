package ajude.psoft.projeto.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Classe que tem a função de ser a entidade curtida que armazena dados do usuário e da curtida em sí.
 * 
 * @author Mauricio Marques da Silva Monte e Natan Ataide de Souza.
 */
@Entity
public class Curtida {

    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private Usuario usuario;

    /**
    * Constrói uma curtida vazia.
	*/
    public Curtida(){
        super();
    }

    /**
    * Constrói uma curtida a partir dos dados passados como parâmetro, incluido o id da curtida.
	* 
	* @param id identificador único da curtida
    * @param usuario usuário que deu a curtida
	*/
    public Curtida(long id, Usuario usuario){
        super();
        this.id = id;
        this.usuario = usuario;
    }

    /**
    * Constrói uma curtida a partir dos dados passados como parâmetro.
	* 
    * @param usuario usuário que deu a curtida
	*/
    public Curtida(Usuario usuario){
        super();
        this.usuario = usuario;
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

}