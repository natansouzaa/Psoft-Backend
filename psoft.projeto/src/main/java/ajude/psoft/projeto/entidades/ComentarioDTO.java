package ajude.psoft.projeto.entidades;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class ComentarioDTO {

    private String texto;
    private String emailDono;
    @Temporal(TemporalType.DATE)
    private Date dataPostagem;
    private long id;

    public ComentarioDTO(String texto, String emailDono, Date dataPostagem, Long id) {
        this.texto = texto;
        this.emailDono = emailDono;
        this.dataPostagem = dataPostagem;
        this.id = id;
    }

    public Comentario transformaParaComentario(){
        Comentario retorno = new Comentario();
        retorno.setTexto(this.texto);
        retorno.setDataPostagem(this.dataPostagem);
        retorno.setRespostas(new ArrayList<>());
        return retorno;
    }

    public String getTexto() {
        return this.texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getEmailDono() {
        return this.emailDono;
    }

    public void setEmailDono(String emailDono) {
        this.emailDono = emailDono;
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
