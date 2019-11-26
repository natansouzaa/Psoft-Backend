package ajude.psoft.projeto.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    public Doacao(){
        super();
    }

    public Doacao(long id, Usuario usuario, float valorDoado, Campanha campanha, Date dataPostagem) {
        super();
        this.id = id;
        this.usuario = usuario;
        this.valorDoado = valorDoado;
        this.campanha = campanha;
        this.dataPostagem = dataPostagem;
    }

    public Doacao(Usuario usuario, float valorDoado, Campanha campanha, Date dataPostagem) {
        super();
        this.usuario = usuario;
        this.valorDoado = valorDoado;
        this.campanha = campanha;
        this.dataPostagem = dataPostagem;
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