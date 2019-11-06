package ajude.psoft.projeto.entidades;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
    private String usuarioDono;
    private String[] comentarios;
    private int curtidas;


    public Campanha() {
        super();
    }

    public Campanha(String nomeCurto, String identificadorURL, String descricao,
        Calendar dataLimite, String status, float meta, String[] doacoes, String usuarioDono,
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
        Calendar dataLimite, String status, float meta, String[] doacoes, String usuarioDono,
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

    public String getNomeCurto() {
        return this.nomeCurto;
    }

    public String getIdentificadorURL() {
        return this.identificadorURL;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public Calendar getDataLimite() {
        return this.dataLimite;
    }

    public String getStatus() {
        return this.status;
    }

    public float getMeta() {
        return this.meta;
    }

    public String[] getDoacoes() {
        return this.doacoes;
    }

    public String getUsuarioDono() {
        return this.usuarioDono;
    }

    public String[] getComentarios() {
        return this.comentarios;
    }

    public int getCurtidas() {
        return this.curtidas;
    }

}