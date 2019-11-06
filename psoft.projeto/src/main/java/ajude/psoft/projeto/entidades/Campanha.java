package ajude.psoft.projeto.entidades;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Campanha {

    @Id
    @GeneratedValue
    private long id;
    private String nomeCurto;
    private String identificadorURL; //sera gerado pelo frontend
    private String descricao;
    private Calendar dataLimite;
    private String status;
    private float meta;
    private String[] doacoes;
    @ManyToOne
    private Usuario usuarioDono;
    private String[] comentarios;
    private int curtidas;


    public Campanha() {
        super();
        this.status = "ativa";
        this.curtidas = 0;
    }

    public Campanha(String nomeCurto, String identificadorURL, String descricao,
        Calendar dataLimite, String status, float meta, String[] doacoes, Usuario usuarioDono,
        String[] comentarios, int curtidas) {
        super();
        this.nomeCurto = nomeCurto;
        this.identificadorURL = identificadorURL;
        this.descricao = descricao;
        this.dataLimite = dataLimite;
        this.status = status;
        this.meta = meta;
        this.doacoes = doacoes;
        this.usuarioDono = usuarioDono;
        this.comentarios = comentarios;
        this.curtidas = curtidas;
    }

    public Campanha(long id, String nomeCurto, String identificadorURL, String descricao,
        Calendar dataLimite, String status, float meta, String[] doacoes, Usuario usuarioDono,
        String[] comentarios, int curtidas) {
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
        this.comentarios = comentarios;
        this.curtidas = curtidas;
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

    public Calendar getDataLimite() {
        return this.dataLimite;
    }

    public void setDataLimite(Calendar dataLimite) {
        this.dataLimite = dataLimite;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
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

    public String[] getComentarios() {
        return this.comentarios;
    }

    public void setComentarios(String[] comentarios) {
        this.comentarios = comentarios;
    }

    public int getCurtidas() {
        return this.curtidas;
    }

    public void setCurtidas(int curtidas) {
        this.curtidas = curtidas;
    }

}