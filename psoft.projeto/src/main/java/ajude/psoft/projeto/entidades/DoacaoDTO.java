package ajude.psoft.projeto.entidades;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class DoacaoDTO {

    private String emailDono;
    private long idCampanha;
    private float valorDoado;
    @Temporal(TemporalType.DATE)
    private Date dataPostagem;

    public DoacaoDTO(String emailDono, long idCampanha, float valorDoado, Date dataPostagem) {
        this.emailDono = emailDono;
        this.idCampanha = idCampanha;
        this.valorDoado = valorDoado;
        this.dataPostagem = dataPostagem;
    }

    public Doacao transformaParaDoacao(){
        Doacao doacao = new Doacao();
        doacao.setDataPostagem(dataPostagem);
        doacao.setValorDoado(valorDoado);
        return doacao;
    }

    public String getEmailDono() {
        return this.emailDono;
    }

    public void setEmailDono(String emailDono) {
        this.emailDono = emailDono;
    }

    public long getIdCampanha() {
        return this.idCampanha;
    }

    public void setIdCampanha(long idCampanha) {
        this.idCampanha = idCampanha;
    }

    public float getValorDoado() {
        return this.valorDoado;
    }

    public void setValorDoado(float valorDoado) {
        this.valorDoado = valorDoado;
    }

    public Date getDataPostagem() {
        return this.dataPostagem;
    }

    public void setDataPostagem(Date dataPostagem) {
        this.dataPostagem = dataPostagem;
    }

}