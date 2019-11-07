package ajude.psoft.projeto.entidades;

import java.util.Calendar;

public class CampanhaDTO {

    private String nomeCurto;
    private String identificadorURL;
    private String descricao;
    private Calendar dataLimite;
    private float meta;
    private String emailDono;

    public CampanhaDTO(){
        super();
    }
    

    public CampanhaDTO(String nomeCurto, String identificadorURL, String descricao, Calendar dataLimite, float meta, String emailDono) {
        super();
        this.nomeCurto = nomeCurto;
        this.identificadorURL = identificadorURL;
        this.descricao = descricao;
        this.dataLimite = dataLimite;
        this.meta = meta;
        this.emailDono = emailDono;
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

    public float getMeta() {
        return this.meta;
    }

    public void setMeta(float meta) {
        this.meta = meta;
    }

    public String getEmailDono() {
        return this.emailDono;
    }

    public void setEmailDono(String emailDono) {
        this.emailDono = emailDono;
    }

}