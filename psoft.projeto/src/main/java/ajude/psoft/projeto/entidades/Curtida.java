package ajude.psoft.projeto.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Curtida {

    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private Usuario usuario;

    public Curtida(){
        super();
    }

    public Curtida(long id, Usuario usuario){
        super();
        this.id = id;
        this.usuario = usuario;
    }

    public Curtida(Usuario usuario){
        super();
        this.usuario = usuario;
    }

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