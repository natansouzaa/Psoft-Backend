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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Controlador que administra as rotas que envolvem as doações, consegue concluir os pedidos que são
 * feitos com a ajuda dos serviços que possui.
 * 
 * @author Mauricio Marques da Silva Monte e Natan Ataide de Souza.
 */
@RestController
@Api(value="API REST de Financiamento coletivo")
public class ControladorComentarios {

    //Inicializando os serviços jwt e de usuarios automaticamente.

    @Autowired
    private ServicoCampanhas servicoCampanhas;
    @Autowired
    private ServicoUsuarios servicoUsuarios;
    @Autowired
    private ServicoComentarios servicoComentarios;
    @Autowired
    private ServicoJWT jwtService;

    /**
     * Rota de PostMapping que adiciona um comentário passado pelo body em uma campanha existente no banco de dados. O
     * usuário que está fazendo o comentário consegue ser capturado graças ao header authorization.
     *
     * @return ResponseEntity<List<Comentario>> entidade de resposta que representa uma lista de comentários que a campanha possui
     */
    @PostMapping("/comentarios/adicionarComentario")
    @ApiOperation(value="Adiciona um novo comentário em uma campanha")
    public ResponseEntity<List<Comentario>> adicionarComentario(@RequestBody ComentarioDTO comentarioDTO, @RequestHeader("Authorization") String header){
        String email = this.jwtService.getSujeitoDoToken(header);
        Comentario comentarioFinal = comentarioDTO.transformaParaComentario();
        comentarioFinal.setUsuario(servicoUsuarios.retornaUsuario(email).get());
        comentarioFinal.setCampanha(servicoCampanhas.retornaCampanha(comentarioDTO.getId()).get());
        this.servicoComentarios.adicionarComentario(comentarioFinal);
        return new ResponseEntity<List<Comentario>>(this.servicoCampanhas.adicionarComentario(comentarioFinal), HttpStatus.CREATED);
    }

    /**
     * Rota de PostMapping que adiciona uma resposta passada pelo body em um comentário existente no banco de dados. O
     * usuário que está fazendo a resposta consegue ser capturado graças ao header authorization.
     *
     * @return ResponseEntity<List<Comentario>> entidade de resposta que representa uma lista de respostas que o comantário possui
     */
    @PostMapping("/comentarios/adicionarResposta")
    @ApiOperation(value="Adiciona uma nova resposta em um comentário")
    public ResponseEntity<List<Comentario>> adicionarResposta(@RequestBody ComentarioDTO comentarioDTO, @RequestHeader("Authorization") String header){
        String email = this.jwtService.getSujeitoDoToken(header);
        Comentario comentarioResposta = comentarioDTO.transformaParaComentario();
        comentarioResposta.setUsuario(servicoUsuarios.retornaUsuario(email).get());
        Comentario comentarioPai = servicoComentarios.retornaComentario(comentarioDTO.getId());
        comentarioResposta.setCampanha(comentarioPai.getCampanha());
        this.servicoComentarios.adicionarComentario(comentarioResposta);
        return new ResponseEntity<List<Comentario>>(this.servicoComentarios.adicionarResposta(comentarioPai, comentarioResposta), HttpStatus.CREATED);
    }

    /**
     * Rota de DeleteMapping que remove um comentário de uma campanha ou de um comentário existente no banco de dados. O
     * usuário que está fazendo a deleção consegue ser capturado graças ao header authorization.
     *
     * @return ResponseEntity<List<Comentario>> entidade de resposta que representa uma lista de comentários que a campanha ou
     * que o comentário possui
     */
    @DeleteMapping("/comentarios/removerComentario/{id}")
    @ApiOperation(value="Remove um comentário de uma campanha ou uma resposta de um comentário")
    public ResponseEntity<List<Comentario>> removerComentario(@PathVariable ("id") long id, @RequestHeader("Authorization") String header){
        String email = this.jwtService.getSujeitoDoToken(header);
        Comentario comentario = servicoComentarios.retornaComentario(id);
        if (email.compareTo(comentario.getUsuario().getEmail()) != 0){
            throw new ResourceUnauthorizedException("Você não está autorizado a remover o comentário de outro usuário");
        }
        this.servicoComentarios.removerComentario(comentario);
        return new ResponseEntity<List<Comentario>>(this.servicoCampanhas.removerComentario(comentario), HttpStatus.CREATED);
    }

}