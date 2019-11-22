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

    public Comentario() {
        super();
    }

    public Comentario(long id, String texto, Usuario usuario, ArrayList<Comentario> respostas, Date dataPostagem, Campanha campanha){
        super();
        this.id = id;
        this.texto = texto;
        this.usuario = usuario;
        this.respostas = respostas;
        this.dataPostagem = dataPostagem;
        this.campanha = campanha;
    }

    public Comentario(String texto, Usuario usuario, ArrayList<Comentario> respostas, Date dataPostagem, Campanha campanha){
        super();
        this.texto = texto;
        this.usuario = usuario;
        this.respostas = respostas;
        this.dataPostagem = dataPostagem;
        this.campanha = campanha;
    }

    public void adicionarResposta(Comentario resposta){
        this.respostas.add(resposta);
    }

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

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", texto='" + texto + '\'' +
                ", usuario=" + usuario +
                ", comentario=" + respostas +
                '}';
    }
}
