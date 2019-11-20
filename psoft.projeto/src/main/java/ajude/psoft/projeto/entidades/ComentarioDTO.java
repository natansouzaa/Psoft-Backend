package ajude.psoft.projeto.entidades;

public class ComentarioDTO {

    private String texto;
    private String email;
    private Comentario comentario;
    private Campanha campanha;

    public ComentarioDTO() {
    }

    public ComentarioDTO(String texto, String email, Comentario comentario, Campanha campanha) {
        this.texto = texto;
        this.email = email;
        this.comentario = comentario;
        this.campanha = campanha;
    }

    public String getTexto() {
        return texto;
    }

    public String getEmail() {
        return email;
    }

    public Comentario getComentario() {
        return comentario;
    }

    public Campanha getCampanha() {
        return campanha;
    }
}
