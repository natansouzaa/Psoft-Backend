package ajude.psoft.projeto.entidades;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class CampanhaDTO {

    private String nomeCurto;
    private String identificadorURL;
    private String descricao;
    @Temporal(TemporalType.DATE)
    private Date dataLimite;
    private float meta;
    private String emailDono;   

    public CampanhaDTO(String nomeCurto, String identificadorURL, String descricao, Date dataLimite, float meta, String emailDono) {
        this.nomeCurto = nomeCurto;
        this.identificadorURL = identificadorURL;
        this.descricao = descricao;
        this.dataLimite = dataLimite;
        this.meta = meta;
        this.emailDono = emailDono;
    }

    public Campanha transformarParaCampanha(){
        Campanha retorno = new Campanha();
        retorno.setDataLimite(this.getDataLimite());
        retorno.setDescricao(this.getDescricao());
        retorno.setCurtidas(new ArrayList<Curtida>());
        retorno.setIdentificadorURL(this.getIdentificadorURL());
        retorno.setMeta(this.getMeta());
        retorno.setNomeCurto(this.getNomeCurto());
        retorno.setStatus(Estado.ATIVA);
        return retorno;
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

    public Date getDataLimite() {
        return this.dataLimite;
    }

    public void setDataLimite(Date dataLimite) {
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