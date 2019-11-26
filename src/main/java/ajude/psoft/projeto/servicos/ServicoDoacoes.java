package ajude.psoft.projeto.servicos;

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

	public Doacao realizaDoacao(Doacao doacaoFinal) {
        Campanha campanha = doacaoFinal.getCampanha();
        campanha.realizaDoacao(doacaoFinal);
		return this.doacoesDAO.save(doacaoFinal);
	}

}