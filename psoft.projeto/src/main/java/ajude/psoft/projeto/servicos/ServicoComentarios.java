package ajude.psoft.projeto.servicos;

import java.util.List;

import org.springframework.stereotype.Service;

import ajude.psoft.projeto.daos.RepositorioComentarios;
import ajude.psoft.projeto.entidades.Comentario;

@Service
public class ServicoComentarios {

    private RepositorioComentarios<Comentario,Long> comentariosDAO;

    public ServicoComentarios(RepositorioComentarios<Comentario,Long> comentariosDAO){
        this.comentariosDAO = comentariosDAO;
    }

    public Comentario adicionarComentario(Comentario novoComentario){
        return this.comentariosDAO.save(novoComentario);
    }

    public List<Comentario> adicionarResposta(Comentario comentarioPai, Comentario resposta){
        comentarioPai.adicionarResposta(resposta);
        return this.comentariosDAO.save(comentarioPai).getRespostas();
    }

    public List<Comentario> removerResposta(Comentario resposta){
        resposta.removerResposta();
        return this.comentariosDAO.findAll();
    }

	public Comentario retornaComentario(long id) {
		return this.comentariosDAO.findById(id).get();
    }
    
    public Comentario removerComentario(Comentario comentario){
        Comentario comentarioOf = this.comentariosDAO.findById(comentario.getId()).get();
        comentarioOf.setTexto("Comentario excluido");
        return comentarioOf;
    }

}