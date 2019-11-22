package ajude.psoft.projeto.servicos;

import org.springframework.stereotype.Service;

import ajude.psoft.projeto.daos.RepositorioComentarios;
import ajude.psoft.projeto.entidades.Campanha;
import ajude.psoft.projeto.entidades.Comentario;

@Service
public class ServicoComentarios {

    private RepositorioComentarios<Comentario,Long> comentariosDAO;

    public ServicoComentarios(RepositorioComentarios<Comentario,Long> comentariosDAO){
        this.comentariosDAO = comentariosDAO;
    }

    public Comentario adicionarComentario(Comentario novoComentario){
        Campanha campanhaDoComentario = novoComentario.getCampanha();
        campanhaDoComentario.adicionarComentario(novoComentario);
        return this.comentariosDAO.save(novoComentario);
    }

}