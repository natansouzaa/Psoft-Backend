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
    private long idCampanha;

    public ComentarioDTO(String texto, String emailDono, Date dataPostagem, Long idCampanha) {
        this.texto = texto;
        this.emailDono = emailDono;
        this.dataPostagem = dataPostagem;
        this.idCampanha = idCampanha;
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

    public long getIdCampanha() {
        return this.idCampanha;
    }

    public void setIdCampanha(long idCampanha) {
        this.idCampanha = idCampanha;
    }

}
