package ajude.psoft.projeto.entidades;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class ComentarioDTO {

    private String texto;
    @Temporal(TemporalType.DATE)
    private Date dataPostagem;
    private String id;

    public ComentarioDTO(String texto, Date dataPostagem, String id) {
        this.texto = texto;
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

    public Date getDataPostagem() {
        return this.dataPostagem;
    }

    public void setDataPostagem(Date dataPostagem) {
        this.dataPostagem = dataPostagem;
    }

    public long getId() {
        return Long.parseLong(this.id);
    }

    public void setIdCampanha(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ComentarioDTO{" +
                "texto='" + texto + '\'' +
                ", dataPostagem=" + dataPostagem +
                ", id='" + id + '\'' +
                '}';
    }
}
