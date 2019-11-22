package ajude.psoft.projeto.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ajude.psoft.projeto.entidades.Comentario;
import ajude.psoft.projeto.entidades.ComentarioDTO;
import ajude.psoft.projeto.servicos.ServicoCampanhas;
import ajude.psoft.projeto.servicos.ServicoComentarios;
import ajude.psoft.projeto.servicos.ServicoUsuarios;

@RestController
public class ControladorComentarios {

    @Autowired
    private ServicoCampanhas servicoCampanhas;
    @Autowired
    private ServicoUsuarios servicoUsuarios;
    @Autowired
    private ServicoComentarios servicoComentarios;

    @PostMapping("/comentarios/adicionarComentario")
    public ResponseEntity<Comentario> adicionarComentario(@RequestBody ComentarioDTO comentarioDTO){
        Comentario comentarioFinal = comentarioDTO.transformaParaComentario();
        comentarioFinal.setUsuario(servicoUsuarios.retornaUsuario(comentarioDTO.getEmailDono()).get());
        comentarioFinal.setCampanha(servicoCampanhas.retornaCampanha(comentarioDTO.getId()).get());
        return new ResponseEntity<Comentario>(servicoComentarios.adicionarComentario(comentarioFinal), HttpStatus.CREATED);
    }

}