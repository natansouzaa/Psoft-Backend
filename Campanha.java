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

/**
 * Classe que tem a função de ser a entidade campanha que armazena dados do usuário, dos comentários, das
 * curtidas, das doações e dos Estados possíveis da campanha.
 * 
 * @author Mauricio Marques da Silva Monte e Natan Ataide de Souza.
 */
@Entity
public class Campanha {

    @Id
    @GeneratedValue
    private long id;
    private String nomeCurto;
    private String identificadorURL;
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

    /**
    * Constrói uma campanha vazia.
	*/
    public Campanha() {
        super();
    }

    /**
    * Constrói uma campanha a partir dos dados passados como parâmetro.
	* 
    * @param nomeCurto nome curto da campanha
    * @param identificadorURL URL da campanha
    * @param descricao descrição da campanha
    * @param dataLimite data limite para arrecadação do dinheiro da campanha
    * @param status estado da campanha
    * @param meta meta de dinheiro que a campanha necessita
    * @param doacoes doações que a campanha recebeu
    * @param usuarioDono usuário dono da campanha
    * @param curtidas curtidas que a campanha recebeu
    * @param comentarios comentários que a campanha possui
    * @param arrecadado total de dinheiro arrecadado pela campanha
	*/
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

    /**
    * Constrói uma campanha a partir dos dados passados como parâmetro, incluido o id da doação.
	* 
	* @param id identificador único da campanha
    * @param nomeCurto nome curto da campanha
    * @param identificadorURL URL da campanha
    * @param descricao descrição da campanha
    * @param dataLimite data limite para arrecadação do dinheiro da campanha
    * @param status estado da campanha
    * @param meta meta de dinheiro que a campanha necessita
    * @param doacoes doações que a campanha recebeu
    * @param usuarioDono usuário dono da campanha
    * @param curtidas curtidas que a campanha recebeu
    * @param comentarios comentários que a campanha possui
    * @param arrecadado total de dinheiro arrecadado pela campanha
	*/
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

    /**
    * Método responsável por realizar uma doação na campanha.
    * 
    * @param doacao doação que será adicionada na campanha
	*/
    public void realizaDoacao(Doacao doacao){
        if (this.getStatus().equals(Estado.ENCERRADA) || this.getStatus().equals(Estado.VENCIDA) || this.getStatus().equals(Estado.CONCLUIDA)){
            throw new ResourceBadRequestException("Esta campanha não está mais disponível para receber doações");
        }
        this.doacoes.add(doacao);
        this.arrecadado += doacao.getValorDoado();
    }

    /**
    * Método responsável por adicionar uma curtida na campanha.
    * 
    * @param curtida curtida que será adicionada na campanha
	*/
    public void adicionarCurtida(Curtida curtida){
        this.curtidas.add(curtida);
    }

    /**
    * Método responsável por remover uma curtida da campanha.
    * 
    * @param curtida curtida que será removida na campanha
	*/
    public void removerCurtida(Curtida curtida){
        this.curtidas.remove(curtida);
    }

    /**
    * Método responsável por adicionar um comentário na campanha.
    * 
    * @param comentario comentário que será adicionado na campanha
	*/
    public void adicionarComentario(Comentario comentario){
        comentarios.add(comentario);
    }

    /**
    * Método responsável por remover um comentário da campanha.
    * 
    * @param comentario comentário que será excluido da campanha
	*/
    public void removerComentario(Comentario comentario){
        for (Comentario c: this.comentarios){
            if (c.getId() == comentario.getId()){
                c.setTexto("");
                break;
            }
        }
    }

    /**
    * getters e setters.
    */

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

    /**
    * Método que retorna uma representação textual da campanha.
    *
    * @return String representação textual da campanha
    */
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