package ajude.psoft.projeto.entidades;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Campanha {

    @Id
    private int id;
    private String nomeCurto;
    private String identificadorURL; //sera gerado pelo frontend
    private String descricao;
    private Calendar dataLimite;
    private String status;
    private float meta;
    private Doacao[] doacoes;
    private Usuario usuarioDono;
    private Comentario[] comentarios;
    private int curtidas;


    public Campanha() {
        super();
    }

    public Campanha(int id, String nomeCurto, String identificadorURL, String descricao,
        Calendar dataLimite, String status, float meta, Doacao[] doacoes, Usuario usuarioDono,
        Comentario[] comentarios, int curtidas) {
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

}