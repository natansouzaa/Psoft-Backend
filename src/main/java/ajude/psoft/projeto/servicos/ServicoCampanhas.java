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

/**
 * Servico que Manipula toda e qualquer informação que envolve as campanhas, aqui é onde
 * mantemos os dados que estão no repositório das campanhas, portanto este é o servico designado
 * para adicionar, remover, listar, buscar, etc. Existe também uma ligação com o repositório de
 * curtidas, para que sejamos capazes dar/retirar curtidas de uma campanha.
 * 
 * @author Mauricio Marques da Silva Monte e Natan Ataide de Souza.
 */
@Service
public class ServicoCampanhas{

    private RepositorioCampanhas<Campanha, Long> campanhasDAO;
    private RepositorioCurtidas<Curtida, Long> curtidasDAO;

    /**
    * Constrói o servico de campanhas a partir dos repositórios necessários para manter os
    * métodos aqui presentes em funcionamento.
	* 
	* @param campanhasDAO repositório das campanhas
	* @param curtidasDAO repositório das curtidas
	*/
    public ServicoCampanhas(RepositorioCampanhas<Campanha, Long> campanhasDAO, RepositorioCurtidas<Curtida, Long> curtidasDAO){
        this.campanhasDAO = campanhasDAO;
        this.curtidasDAO = curtidasDAO;
    }

    /**
    * Método responsável por adicionar um novo comentário na campanha.
	* 
    * @param comentario comentário a ser adicionado na campanha
    * @return List<Comentario> comentarios que existem na campanha
	*/
    public List<Comentario> adicionarComentario(Comentario comentario){
        Campanha campanhaAux = comentario.getCampanha();
        Campanha campanha = this.campanhasDAO.findById(campanhaAux.getId()).get();
        campanha.adicionarComentario(comentario);
        return this.campanhasDAO.save(campanha).getComentarios();
    }

    /**
    * Método responsável por remover um comentário da campanha.
	* 
    * @param comentario comentário a ser removido da campanha
    * @return List<Comentario> restante dos comentarios que existem na campanha
	*/
    public List<Comentario> removerComentario(Comentario comentario) {
        Campanha campanhaAux = comentario.getCampanha();
        Campanha campanha = this.campanhasDAO.findById(campanhaAux.getId()).get();
        campanha.removerComentario(comentario);
        return this.campanhasDAO.save(campanha).getComentarios();
	}

    /**
    * Método responsável por adicionar uma nova campanha no bando de dados.
	* 
    * @param campanha campanha a ser adicionada
    * @return Campanha campanha que foi salva no bando de dados
	*/
    public Campanha adicionaCampanha(Campanha campanha){
        return this.campanhasDAO.save(campanha);
    }

    /**
    * Método responsável por retornar uma campanha que já existe no bando de dados.
	* 
    * @param id chave primária da campanha a ser retornada
    * @return Optional<Campanha> optional da campanha procurada
	*/
    public Optional<Campanha> retornaCampanha(Long id){
        this.modificaStatusCampanhas();
        return this.campanhasDAO.findById(id);
    }

    /**
    * Método responsável por retornar uma campanha que já existe no bando de dados a partir de seu identificadorURL.
	* 
    * @param identificadorURL identificadorURL da campanha a ser retornada
    * @return Campanha campanha que foi procurada
	*/
	public Campanha retornaCampanhaPeloIdentificadorURL(String identificadorURL) {
        this.modificaStatusCampanhas();
		return this.campanhasDAO.findByidentificadorURL(identificadorURL);
	}

    /**
    * Método responsável por retornar uma lista das campanhas que possuem uma certa String em seu nomeCurto
	* 
    * @param busca String que o usuário deseja fazer a filtragem
    * @param todos Boolean que define se serão listadas campanhas que possuem qualquer estado, ou somente as ativas
    * @return List<Campanha> lista das campanhas que atendem a filtragem
	*/
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

    /**
    * Método responsável por adicionar ou retirar curtidas de uma campanha, vai depender se o usuário recebido já curtiu a campanha.
	* 
    * @param campanha campanha onde será feita a alteração
    * @param usuario usuário que irá dar ou retirar uma curtida
    * @return Campanha campanha onde foi feita a alteração
	*/
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

    /**
    * Método responsável por retornar uma lista das campanhas que foram criadas por um certo usuário e também campanhas que
    * este usuário contribuiu com doações.
	* 
    * @param usuario usuario que sevirá para a filtragem
    * @return List<Campanha> lista das campanhas que atendem a filtragem
	*/
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

    /**
    * Método responsável por editar uma campanha que já existe na base de dados.
	* 
    * @param novaDescricao nova descrição da campanha
    * @param campanha campanha que receberá a modificação
    * @return Campanha campanha já com as mudanças feitas
	*/
	public Campanha editaCampanha(Campanha campanha, String novaDescricao) {
        Campanha campanhaSujeito = this.campanhasDAO.findById(campanha.getId()).get();
        campanhaSujeito.setDescricao(novaDescricao);
        return this.campanhasDAO.save(campanhaSujeito);
	}

    /**
    * Método responsável por fazer uma verificação nas campanhas que existem no bando de dados.
    * É dele a responsabilidade de fazer as checagens para atribuir os estados corretos às campanha.
	* 
	*/
    public void modificaStatusCampanhas(){
        Date dataAtual = new Date();
        for (Campanha c: this.campanhasDAO.findAll()){
            if (dataAtual.compareTo(c.getDataLimite()) > 1) {
                if (c.getArrecadado() >= c.getMeta()){
                    c.setStatus(Estado.CONCLUIDA);
                    this.campanhasDAO.save(c);
                } else {
                    c.setStatus(Estado.VENCIDA);
                    this.campanhasDAO.save(c);
                }
            }
        }
    }

    /**
    * Método que retorna as 5 campanhas que estão mais próximas de bater a meta, também retorna as que já
    * alcançaram a meta mas ainda não foram encerradas.
	* 
    * @return List<Campanha> lista das 5 campanhas que atendem a filtragem feita
	*/
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

    /**
    * Método que retorna as 5 campanhas que estão mais próximas de alcançar o deadline.
	* 
    * @return List<Campanha> lista das 5 campanhas que atendem a filtragem feita
	*/
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

    /**
    * Método que retorna as 5 campanhas que possuem o maior número de curtidas.
	* 
    * @return List<Campanha> lista das 5 campanhas que atendem a filtragem feita
	*/
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