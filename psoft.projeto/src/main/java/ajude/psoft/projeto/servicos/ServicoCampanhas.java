package ajude.psoft.projeto.servicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ajude.psoft.projeto.entidades.*;
import org.springframework.stereotype.Service;

import ajude.psoft.projeto.daos.RepositorioCampanhas;

@Service
public class ServicoCampanhas{

    private RepositorioCampanhas<Campanha, Long> campanhasDAO;

    public ServicoCampanhas(RepositorioCampanhas<Campanha, Long> campanhasDAO){
        this.campanhasDAO = campanhasDAO;
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
        return this.campanhasDAO.save(campanha).getComentarios(); //seraaaaaaaaa?
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

}