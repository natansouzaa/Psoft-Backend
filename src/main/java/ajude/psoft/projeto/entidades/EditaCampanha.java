package ajude.psoft.projeto.entidades;

public class EditaCampanha {

    private String identificadorURL;
    private String novaDescricao;

    public EditaCampanha(String identificadorURL, String novaDescricao){
        this.identificadorURL = identificadorURL;
        this.novaDescricao = novaDescricao;
    }

    public String getIdentificadorURL() {
        return this.identificadorURL;
    }

    public void setIdentificadorURL(String identificadorURL) {
        this.identificadorURL = identificadorURL;
    }

    public String getNovaDescricao() {
        return this.novaDescricao;
    }

    public void setNovaDescricao(String novaDescricao) {
        this.novaDescricao = novaDescricao;
    }

}