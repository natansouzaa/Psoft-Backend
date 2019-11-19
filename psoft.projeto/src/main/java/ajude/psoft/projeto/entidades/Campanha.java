package ajude.psoft.projeto.entidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Campanha {

    @Id
    @GeneratedValue
    private long id;


    private String nomeCurto;
    private String identificadorURL; //sera gerado pelo frontend
    private String descricao;
    @Temporal(TemporalType.DATE)
    private Date dataLimite;
    private Estado status;
    private float meta;
    private String[] doacoes;
    @ManyToOne
    private Usuario usuarioDono;
    private List<Comentario> comentarios = new ArrayList<>();
    private int curtidas;


    public Campanha() {
        super();
    }

    public Campanha(String nomeCurto, String identificadorURL, String descricao,
                    Date dataLimite, Estado status, float meta, String[] doacoes, Usuario usuarioDono,
                    int curtidas) {
        super();
        this.nomeCurto = nomeCurto;
        this.identificadorURL = identificadorURL;
        this.descricao = descricao;
        this.dataLimite = dataLimite;
        this.status = status;
        this.meta = meta;
        this.doacoes = doacoes;
        this.usuarioDono = usuarioDono;
        this.curtidas = curtidas;
    }

    public Campanha(long id, String nomeCurto, String identificadorURL, String descricao,
        Date dataLimite, Estado status, float meta, String[] doacoes, Usuario usuarioDono,
         int curtidas) {
        super();
        this.id = id;
        this.nomeCurto = nomeCurto;
        this.identificadorURL = identificadorURL;
        this.descricao = descricao;
        this.dataLimite = dataLimite;
        this.status = status;
        this.meta = meta;
        this.doacoes = doacoes;
        this.usuarioDono = usuarioDono;
        this.curtidas = curtidas;
    }

    public List<Comentario> adicionarComentario(Comentario comentario){
        comentarios.add(comentario);
        return comentarios;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public Estado getStatus() {
        return this.status;
    }

    public void setStatus(Estado status) {
        this.status = status;
    }

    public float getMeta() {
        return this.meta;
    }

    public void setMeta(float meta) {
        this.meta = meta;
    }

    public String[] getDoacoes() {
        return this.doacoes;
    }

    public void setDoacoes(String[] doacoes) {
        this.doacoes = doacoes;
    }

    public Usuario getUsuarioDono() {
        return this.usuarioDono;
    }

    public void setUsuarioDono(Usuario usuarioDono) {
        this.usuarioDono = usuarioDono;
    }

    public List<Comentario> getComentarios() {
        return this.comentarios;
    }

    public int getCurtidas() {
        return this.curtidas;
    }

    public void setCurtidas(int curtidas) {
        this.curtidas = curtidas;
    }

    @Override
    public String toString() {
        return "Campanha{" +
                "id=" + id +
                ", nomeCurto='" + nomeCurto + '\'' +
                ", identificadorURL='" + identificadorURL + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataLimite=" + dataLimite +
                ", status=" + status +
                ", meta=" + meta +
                ", doacoes=" + Arrays.toString(doacoes) +
                ", usuarioDono=" + usuarioDono +
                ", comentarios=" + comentarios +
                ", curtidas=" + curtidas +
                '}';
    }
}