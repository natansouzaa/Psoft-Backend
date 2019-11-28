package ajude.psoft.projeto.servicos;

import java.util.List;

import org.springframework.stereotype.Service;

import ajude.psoft.projeto.daos.RepositorioComentarios;
import ajude.psoft.projeto.entidades.Comentario;

/**
 * Servico que Manipula toda e qualquer informação que envolve os comentários, aqui é onde
 * mantemos os dados que estão no repositório dos comentários, portanto este é o servico designado
 * para adicionar, remover e listar comentários.
 * 
 * @author Mauricio Marques da Silva Monte e Natan Ataide de Souza.
 */
@Service
public class ServicoComentarios {

    private RepositorioComentarios<Comentario,Long> comentariosDAO;

    /**
    * Constrói o servico de comentários a partir do repositório necessário para manter os
    * métodos aqui presentes em funcionamento.
	* 
	* @param comentariosDAO repositório dos comentários
	*/
    public ServicoComentarios(RepositorioComentarios<Comentario,Long> comentariosDAO){
        this.comentariosDAO = comentariosDAO;
    }

    /**
    * Método responsável por adicionar um novo comentário no banco de dados.
	* 
    * @param novoComentario comentário a ser adicionado no banco de dados
    * @return Comentario comentario que foi salvo
	*/
    public Comentario adicionarComentario(Comentario novoComentario){
        return this.comentariosDAO.save(novoComentario);
    }

    /**
    * Método responsável por adicionar uma nova resposta em um comentário
	* 
    * @param comentarioPai comentário onde a resposta será adicionada
    * @param resposta comentario a ser adicionado no comentarioPai
    * @return List<Comentario> lista das respostas que existem no comentarioPai
	*/
    public List<Comentario> adicionarResposta(Comentario comentarioPai, Comentario resposta){
        comentarioPai.adicionarResposta(resposta);
        return this.comentariosDAO.save(comentarioPai).getRespostas();
    }

    /**
    * Método responsável por remover uma resposta de um comentário, porém mantém no banco de dados.
	* 
    * @param resposta resposta a ser removida
    * @return List<Comentario> lista das respostas que existem no comentário
	*/
    public List<Comentario> removerResposta(Comentario resposta){
        resposta.removerResposta();
        return this.comentariosDAO.findAll();
    }

    /**
    * Método que retorna um comentário que existe no banco de dados.
	* 
    * @param id chave primária do comentário a ser retornado
    * @return Comentario comentario retornado
	*/
	public Comentario retornaComentario(long id) {
		return this.comentariosDAO.findById(id).get();
    }
    
    /**
    * Método responsável por remover o comentário de uma campanha, porém mantém no banco de dados.
	* 
    * @param comentario comentário a ser removido
    * @return Comentario comentário que foi removido
	*/
    public Comentario removerComentario(Comentario comentario){
        Comentario comentarioOf = this.comentariosDAO.findById(comentario.getId()).get();
        comentarioOf.setTexto("");
        return comentarioOf;
    }

}