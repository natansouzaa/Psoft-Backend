package ajude.psoft.projeto.entidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Comentario {

    @Id
    @GeneratedValue
    private long id;

    private String texto;
    private Usuario usuario;

    @ManyToOne //onetomany ou manytoone, como colocar que esse 1 é opcional?
    private List<Comentario> respostas = new ArrayList<Comentario>();

    public Comentario() {
    }

    public Comentario(long id, String texto, Usuario usuario) {
        this.id = id;
        this.texto = texto;
        this.usuario = usuario;

    }
    public Comentario(ComentarioDTO novoComentario, Usuario usuario){
        this.texto = novoComentario.getTexto();
        this.usuario = usuario;
    }

    public List<Comentario> adicionarResposta(Comentario resposta){
        respostas.add(resposta);
        return respostas;
    }

    public long getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<Comentario> getRespostas() {
        return respostas;
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
