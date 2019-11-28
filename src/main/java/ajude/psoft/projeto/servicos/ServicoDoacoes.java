package ajude.psoft.projeto.servicos;

import org.springframework.stereotype.Service;

import ajude.psoft.projeto.daos.RepositorioDoacoes;
import ajude.psoft.projeto.entidades.Campanha;
import ajude.psoft.projeto.entidades.Doacao;

/**
 * Servico que Manipula toda e qualquer informação que envolve as doações, aqui é onde
 * mantemos os dados que estão no repositório das doações, portanto este é o servico designado
 * para realizar as doações.
 * 
 * @author Mauricio Marques da Silva Monte e Natan Ataide de Souza.
 */
@Service
public class ServicoDoacoes {

    private RepositorioDoacoes<Doacao, Long> doacoesDAO;

    /**
    * Constrói o servico de doações a partir do repositório necessário para manter os
    * métodos aqui presentes em funcionamento.
	* 
	* @param doacoesDAO repositório das doações
	*/
    public ServicoDoacoes(RepositorioDoacoes<Doacao, Long> doacoesDAO){
        this.doacoesDAO = doacoesDAO;
    }

    /**
	* Método responsável por realizar uma nova doação, mantendo essa doação no repositório.
    * 
    * @param doacaoFinal doação a ser efetuada
	* @return Campanha campanha que recebeu a doação
	*/
	public Campanha realizaDoacao(Doacao doacaoFinal) {
        Campanha campanha = doacaoFinal.getCampanha();
        campanha.realizaDoacao(doacaoFinal);
        return this.doacoesDAO.save(doacaoFinal).getCampanha();
	}

}