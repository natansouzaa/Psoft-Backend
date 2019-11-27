package ajude.psoft.projeto.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import ajude.psoft.projeto.entidades.Comentario;
import ajude.psoft.projeto.entidades.ComentarioDTO;
import ajude.psoft.projeto.erros.ResourceUnauthorizedException;
import ajude.psoft.projeto.servicos.ServicoCampanhas;
import ajude.psoft.projeto.servicos.ServicoComentarios;
import ajude.psoft.projeto.servicos.ServicoJWT;
import ajude.psoft.projeto.servicos.ServicoUsuarios;

@RestController
public class ControladorComentarios {

    @Autowired
    private ServicoCampanhas servicoCampanhas;
    @Autowired
    private ServicoUsuarios servicoUsuarios;
    @Autowired
    private ServicoComentarios servicoComentarios;
    @Autowired
    private ServicoJWT jwtService;

    @PostMapping("/comentarios/adicionarComentario")
    public ResponseEntity<List<Comentario>> adicionarComentario(@RequestBody ComentarioDTO comentarioDTO, @RequestHeader("Authorization") String header){
        String email = this.jwtService.getSujeitoDoToken(header);
        Comentario comentarioFinal = comentarioDTO.transformaParaComentario();
        comentarioFinal.setUsuario(servicoUsuarios.retornaUsuario(email).get());
        comentarioFinal.setCampanha(servicoCampanhas.retornaCampanha(comentarioDTO.getId()).get());
        this.servicoComentarios.adicionarComentario(comentarioFinal);
        return new ResponseEntity<List<Comentario>>(this.servicoCampanhas.adicionarComentario(comentarioFinal), HttpStatus.CREATED);
    }

    @PostMapping("/comentarios/adicionarResposta")
    public ResponseEntity<List<Comentario>> adicionarResposta(@RequestBody ComentarioDTO comentarioDTO, @RequestHeader("Authorization") String header){
        String email = this.jwtService.getSujeitoDoToken(header);
        Comentario comentarioResposta = comentarioDTO.transformaParaComentario();
        comentarioResposta.setUsuario(servicoUsuarios.retornaUsuario(email).get());
        Comentario comentarioPai = servicoComentarios.retornaComentario(comentarioDTO.getId());
        comentarioResposta.setCampanha(comentarioPai.getCampanha());
        this.servicoComentarios.adicionarComentario(comentarioResposta);
        return new ResponseEntity<List<Comentario>>(this.servicoComentarios.adicionarResposta(comentarioPai, comentarioResposta), HttpStatus.CREATED);
    }

    @DeleteMapping("/comentarios/removerComentario/{id}")
    public ResponseEntity<List<Comentario>> removerComentario(@PathVariable ("id") long id, @RequestHeader("Authorization") String header){
        String email = this.jwtService.getSujeitoDoToken(header);
        Comentario comentario = servicoComentarios.retornaComentario(id);
        if (email != (comentario.getUsuario().getEmail())){
            throw new ResourceUnauthorizedException("Você não está autorizado a remover o comentário de outro usuário");
        }
        this.servicoComentarios.removerComentario(comentario);
        return new ResponseEntity<List<Comentario>>(this.servicoCampanhas.removerComentario(comentario), HttpStatus.CREATED);
    }

}