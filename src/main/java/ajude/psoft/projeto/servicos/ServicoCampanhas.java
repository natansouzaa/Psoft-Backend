package ajude.psoft.projeto.servicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ajude.psoft.projeto.entidades.*;

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
        return this.campanhasDAO.findById(id);
    }

	public Campanha retornaCampanhaPeloIdentificadorURL(String identificadorURL) {
		return this.campanhasDAO.findByidentificadorURL(identificadorURL);
	}

	public List<Campanha> retornaCampanhasPelaBusca(String busca) {
        List<Campanha> todasAsCampanhas = this.campanhasDAO.findAll();
        List<Campanha> campanhasSelecionadas = new ArrayList<Campanha>();
        for (Campanha campanha : todasAsCampanhas) {
            if (campanha.getNomeCurto().toLowerCase().contains(busca.toLowerCase())){
                campanhasSelecionadas.add(campanha);
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
            }
        }
		return resultado;
	}

	public Campanha editaCampanha(Campanha campanha, String novaDescricao) {
        Campanha campanhaSujeito = this.campanhasDAO.findById(campanha.getId()).get();
        campanhaSujeito.setDescricao(novaDescricao);
        return this.campanhasDAO.save(campanhaSujeito);
	}

}