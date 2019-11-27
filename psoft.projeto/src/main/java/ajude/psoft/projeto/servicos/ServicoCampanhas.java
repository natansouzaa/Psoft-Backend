package ajude.psoft.projeto.servicos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import ajude.psoft.projeto.entidades.*;
import ajude.psoft.projeto.ordenacoes.OrdenaPelaCurtida;
import ajude.psoft.projeto.ordenacoes.OrdenaPelaData;
import ajude.psoft.projeto.ordenacoes.OrdenaPelaMeta;

import org.springframework.stereotype.Service;

import ajude.psoft.projeto.daos.RepositorioCampanhas;
import ajude.psoft.projeto.daos.RepositorioCurtidas;

@Service
public class ServicoCampanhas{

    private RepositorioCampanhas<Campanha, Long> campanhasDAO;
    private RepositorioCurtidas<Curtida, Long> curtidasDAO;

    public ServicoCampanhas(RepositorioCampanhas<Campanha, Long> campanhasDAO, RepositorioCurtidas<Curtida, Long> curtidasDAO){
        this.campanhasDAO = campanhasDAO;
        this.curtidasDAO = curtidasDAO;
    }

    public List<Comentario> adicionarComentario(Comentario comentario){
        Campanha campanhaAux = comentario.getCampanha();
        Campanha campanha = this.campanhasDAO.findById(campanhaAux.getId()).get();
        campanha.adicionarComentario(comentario);
        return this.campanhasDAO.save(campanha).getComentarios();
    }

    public List<Comentario> removerComentario(Comentario comentario) {
        Campanha campanhaAux = comentario.getCampanha();
        Campanha campanha = this.campanhasDAO.findById(campanhaAux.getId()).get();
        campanha.removerComentario(comentario);
        return this.campanhasDAO.save(campanha).getComentarios();
	}

    public Campanha adicionaCampanha(Campanha campanha){
        return this.campanhasDAO.save(campanha);
    }

    public Optional<Campanha> retornaCampanha(Long id){
        this.modificaStatusCampanhas();
        return this.campanhasDAO.findById(id);
    }

	public Campanha retornaCampanhaPeloIdentificadorURL(String identificadorURL) {
        this.modificaStatusCampanhas();
		return this.campanhasDAO.findByidentificadorURL(identificadorURL);
	}

	public List<Campanha> retornaCampanhasPelaBusca(String busca, Boolean todos) {
        List<Campanha> todasAsCampanhas = this.campanhasDAO.findAll();
        List<Campanha> campanhasSelecionadas = new ArrayList<Campanha>();
        for (Campanha campanha : todasAsCampanhas) {
            if (campanha.getNomeCurto().toLowerCase().contains(busca.toLowerCase())){
                if (todos){
                    campanhasSelecionadas.add(campanha);
                } else {
                    if (campanha.getStatus() == Estado.ATIVA){
                        campanhasSelecionadas.add(campanha);
                    }
                }
            }
        }
        return campanhasSelecionadas;
	}

    public Campanha relacaoCurtida(Campanha campanha, Usuario usuario){
        Campanha campanhaFinal = this.campanhasDAO.findById(campanha.getId()).get();
        for (Curtida c: campanhaFinal.getCurtidas()){
            if (c.getUsuario().equals(usuario)){
                campanhaFinal.removerCurtida(c);
                this.curtidasDAO.delete(c);
                return campanhaFinal;
            }
        }

        Curtida curtida = new Curtida(usuario);
        this.curtidasDAO.save(curtida);
        campanhaFinal.adicionarCurtida(curtida);
        return this.campanhasDAO.save(campanhaFinal);
    }

	public List<Campanha> retornaCampanhasUsuario(Usuario usuario) {
        List<Campanha> resultado = new ArrayList<Campanha>();
        for (Campanha c: this.campanhasDAO.findAll()){
            if (c.getUsuarioDono().equals(usuario)){
                resultado.add(c);
            } else {
                for (Doacao d: c.getDoacoes()){
                    if (d.getUsuario().equals(usuario)){
                        resultado.add(c);
                        break;
                    }
                }
            }

        }
        this.modificaStatusCampanhas();
		return resultado;
	}

	public Campanha editaCampanha(Campanha campanha, String novaDescricao) {
        Campanha campanhaSujeito = this.campanhasDAO.findById(campanha.getId()).get();
        campanhaSujeito.setDescricao(novaDescricao);
        return this.campanhasDAO.save(campanhaSujeito);
	}

    public void modificaStatusCampanhas(){
        Date dataAtual = new Date();
        for (Campanha c: this.campanhasDAO.findAll()){
            if (dataAtual.getTime() > c.getDataLimite().getTime()){
                if (c.getArrecadado() > c.getMeta()){
                    c.setStatus(Estado.CONCLUIDA);
                    this.campanhasDAO.save(c);
                } else {
                    c.setStatus(Estado.VENCIDA);
                    this.campanhasDAO.save(c);
                }
            }
        }
    }

	public List<Campanha> retornaCampanhasPelaMeta() {
        ArrayList<Campanha> listaProv = (ArrayList<Campanha>) this.campanhasDAO.findAll();
        Collections.sort(listaProv, new OrdenaPelaMeta());
        List<Campanha> listaRetorno = new ArrayList<Campanha>();
        int contador = 0;
        for(Campanha c: listaProv){
            if (contador >= 5){
                break;
            }
            if (c.getStatus().equals(Estado.ATIVA)){
                listaRetorno.add(c);
                contador++;
            }
        }
		return listaRetorno;
	}

	public List<Campanha> retornaCampanhasPelaData() {
		ArrayList<Campanha> listaProv = (ArrayList<Campanha>) this.campanhasDAO.findAll();
        Collections.sort(listaProv, new OrdenaPelaData());
        List<Campanha> listaRetorno = new ArrayList<Campanha>();
        int contador = 0;
        for(Campanha c: listaProv){
            if (contador >= 5){
                break;
            }
            if (c.getStatus().equals(Estado.ATIVA)){
                listaRetorno.add(c);
                contador++;
            }
        }
		return listaRetorno;
	}

	public List<Campanha> retornaCampanhasPelaCurtida() {
		ArrayList<Campanha> listaProv = (ArrayList<Campanha>) this.campanhasDAO.findAll();
        Collections.sort(listaProv, new OrdenaPelaCurtida());
        List<Campanha> listaRetorno = new ArrayList<Campanha>();
        int contador = 0;
        for(Campanha c: listaProv){
            if (contador >= 5){
                break;
            }
            if (c.getStatus().equals(Estado.ATIVA)){
                listaRetorno.add(c);
                contador++;
            }
        }
		return listaRetorno;
	}

}