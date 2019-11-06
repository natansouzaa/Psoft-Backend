package ajude.psoft.projeto.servicos;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ajude.psoft.projeto.daos.RepositorioCampanhas;
import ajude.psoft.projeto.entidades.Campanha;

@Service
public class ServicoCampanhas{

    private RepositorioCampanhas<Campanha, Long> campanhasDAO; 

    public ServicoCampanhas(RepositorioCampanhas<Campanha, Long> campanhasDAO){
        this.campanhasDAO = campanhasDAO;
    }

    public Campanha adicionaCampanha(Campanha campanha){
        return this.campanhasDAO.save(campanha);
    }

    public Optional<Campanha> retornaCampanha(Long id){
        return this.campanhasDAO.findById(id);
    }

}