package ajude.psoft.projeto.controladores;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ajude.psoft.projeto.entidades.Campanha;
import ajude.psoft.projeto.entidades.Usuario;
import ajude.psoft.projeto.erros.ResourceBadRequestException;
import ajude.psoft.projeto.servicos.ServicoCampanhas;
import ajude.psoft.projeto.servicos.ServicoUsuarios;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Controlador que administra as rotas que envolvem os usuários, consegue concluir os pedidos que são
 * feitos com a ajuda dos serviços que possui.
 * 
 * @author Mauricio Marques da Silva Monte e Natan Ataide de Souza.
 */
@RestController
@Api(value="API REST de Financiamento coletivo")
public class ControladorUsuarios {

    //Inicializando os serviços de campanhas e usuarios automaticamente. 

    @Autowired
    private ServicoUsuarios servicoUsuarios;
    @Autowired
    private ServicoCampanhas servicoCampanhas;

    /**
     * Rota de PostMapping que adiciona um usuário com email, nome, cartão de crédiro e senha. O email será o login do usuário
     * e também deve ser seu identificador único. Recebe um usuarioDTO no body para auxiliar no cadastro do usuário.
     *
     * @return ResponseEntity<Usuario> entidade de resposta que representa um usuário
     */
    @PostMapping("/usuarios")
    @ApiOperation(value="Cadastra um usuário")
    public ResponseEntity<Usuario> adicionaUsuario(@RequestBody Usuario novoUsuario){
        if (servicoUsuarios.existeNoDAO(novoUsuario.getEmail())){
            throw new ResourceBadRequestException("Email já cadastrado");
        }
        return new ResponseEntity<Usuario>(servicoUsuarios.adicionaUsuario(novoUsuario), HttpStatus.CREATED);
    }

    /**
     * Rota de GetMapping que retorna um usuário a partir do seu username(email) que será passado como PathVariable na URL.
     *
     * @return ResponseEntity<Usuario> entidade de resposta que representa um usuário
     */
    @GetMapping("/usuarios/{username}")
    @ApiOperation(value="Retorna um usuário pelo email")
    public ResponseEntity<Usuario> retornaUsuarioPeloUsername(@PathVariable("username") String username){
        try {
            return new ResponseEntity<Usuario>(this.servicoUsuarios.retornaUsuario(username).get(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new ResourceBadRequestException("Este usuario não existe");
        }
        
    }

    /**
     * Rota de GetMapping que retorna uma lista de campanhas que um usuário criou ou contribuiu com alguma doação. Este
     * usuário é recuperado a partir do seu email que será passado como PathVariable na URL.
     *
     * @return ResponseEntity<Usuario> entidade de resposta que representa um usuário
     */
    @GetMapping("/usuarios/campanhas/{email}")
    @ApiOperation(value="Retorna uma lista de campanhas de um usuário")
    public ResponseEntity<List<Campanha>> retornaCampanhasUsuario(@PathVariable ("email") String email){
        Usuario usuario;
        try {
            usuario = this.servicoUsuarios.retornaUsuario(email).get();
        } catch (NoSuchElementException e) {
            throw new ResourceBadRequestException("Este usuario não existe");
        }
        return new ResponseEntity<List<Campanha>>(this.servicoCampanhas.retornaCampanhasUsuario(usuario), HttpStatus.OK);
    }

}