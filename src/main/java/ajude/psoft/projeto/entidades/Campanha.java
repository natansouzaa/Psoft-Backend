package ajude.psoft.projeto.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ajude.psoft.projeto.erros.ResourceBadRequestException;

@Entity
public class Campanha {

    @Id
    @GeneratedValue
    private long id;
    private String nomeCurto;
    private String identificadorURL; //sera gerado pelo frontend
    private String descricao;
    @Temporal(TemporalType.DATE)
    private Date dataLimite;
    private Estado status;
    private float arrecadado;
    private float meta;
    @ManyToOne
    private Usuario usuarioDono;
    @OneToMany
    private List<Comentario> comentarios;
    @OneToMany
    private List<Curtida> curtidas;
    @OneToMany
    private List<Doacao> doacoes;

    public Campanha() {
        super();
    }

    public Campanha(String nomeCurto, String identificadorURL, String descricao,
                    Date dataLimite, Estado status, float meta, ArrayList<Doacao> doacoes, Usuario usuarioDono,
                    ArrayList<Curtida> curtidas, ArrayList<Comentario> comentarios, float arrecadado) {
        super();
        this.nomeCurto = nomeCurto;
        this.identificadorURL = identificadorURL;
        this.descricao = descricao;
        this.dataLimite = dataLimite;
        this.status = status;
        this.meta = meta;
        this.doacoes = doacoes;
        this.usuarioDono = usuarioDono;
        this.curtidas = curtidas;
        this.comentarios = comentarios;
        this.arrecadado = arrecadado;
    }

    public Campanha(long id, String nomeCurto, String identificadorURL, String descricao,
                    Date dataLimite, Estado status, float meta, ArrayList<Doacao> doacoes, Usuario usuarioDono,
                    ArrayList<Curtida> curtidas, ArrayList<Comentario> comentarios, float arrecadado) {
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
        this.curtidas = curtidas;
        this.comentarios = comentarios;
        this.arrecadado = arrecadado;
    }

    public void realizaDoacao(Doacao doacao){
        if (this.getStatus().equals(Estado.ENCERRADA) || this.getStatus().equals(Estado.VENCIDA) || this.getStatus().equals(Estado.CONCLUIDA)){
            throw new ResourceBadRequestException("Esta campanha não está mais disponível para receber doações");
        }
        this.doacoes.add(doacao);
        this.arrecadado += doacao.getValorDoado();
        if (this.arrecadado >= this.meta){
            this.setStatus(Estado.CONCLUIDA);
        }
    }

    public void adicionarCurtida(Curtida curtida){
        this.curtidas.add(curtida);
    }

    public void removerCurtida(Curtida curtida){
        this.curtidas.remove(curtida);
    }

    public void adicionarComentario(Comentario comentario){
        comentarios.add(comentario);
    }

    public void removerComentario(Comentario comentario){
        for (Comentario c: this.comentarios){
            if (c.getId() == comentario.getId()){
                c.setTexto("");
                break;
            }
        }
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Estado getStatus() {
        return this.status;
    }

    public void setStatus(Estado status) {
        this.status = status;
    }

    public float getMeta() {
        return this.meta;
    }

    public void setMeta(float meta) {
        this.meta = meta;
    }

    public float getArrecadado() {
        return this.arrecadado;
    }

    public void setArrecadado(float arrecadado) {
        this.arrecadado = arrecadado;
    }

    public List<Doacao> getDoacoes() {
        return this.doacoes;
    }

    public void setDoacoes(ArrayList<Doacao> doacoes) {
        this.doacoes = doacoes;
    }

    public Usuario getUsuarioDono() {
        return this.usuarioDono;
    }

    public void setUsuarioDono(Usuario usuarioDono) {
        this.usuarioDono = usuarioDono;
    }

    public List<Comentario> getComentarios() {
        return this.comentarios;
    }

    public void setComentario(ArrayList<Comentario> comentarios){
        this.comentarios = comentarios;
    }

    public List<Curtida> getCurtidas() {
        return this.curtidas;
    }

    public void setCurtidas(ArrayList<Curtida> curtidas) {
        this.curtidas = curtidas;
    }

    @Override
    public String toString() {
        return "Campanha{" +
                "id=" + id +
                ", nomeCurto='" + nomeCurto + '\'' +
                ", identificadorURL='" + identificadorURL + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataLimite=" + dataLimite +
                ", status=" + status +
                ", meta=" + meta +
                ", doacoes=" + doacoes +
                ", usuarioDono=" + usuarioDono +
                ", comentarios=" + comentarios +
                ", curtidas=" + curtidas +
                '}';
    }
}