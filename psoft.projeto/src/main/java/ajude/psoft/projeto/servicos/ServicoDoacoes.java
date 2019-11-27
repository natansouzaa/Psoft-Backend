package ajude.psoft.projeto.servicos;

import java.util.List;

import org.springframework.stereotype.Service;

import ajude.psoft.projeto.daos.RepositorioDoacoes;
import ajude.psoft.projeto.entidades.Campanha;
import ajude.psoft.projeto.entidades.Doacao;

@Service
public class ServicoDoacoes {

    private RepositorioDoacoes<Doacao, Long> doacoesDAO;

    public ServicoDoacoes(RepositorioDoacoes<Doacao, Long> doacoesDAO){
        this.doacoesDAO = doacoesDAO;
    }

	public List<Doacao> realizaDoacao(Doacao doacaoFinal) {
        Campanha campanha = doacaoFinal.getCampanha();
        campanha.realizaDoacao(doacaoFinal);
        this.doacoesDAO.save(doacaoFinal);
        return this.doacoesDAO.findAll();
        
	}

}