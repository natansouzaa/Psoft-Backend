package ajude.psoft.projeto.controladores;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ajude.psoft.projeto.entidades.Campanha;
import ajude.psoft.projeto.entidades.Usuario;
import ajude.psoft.projeto.erros.ResourceBadRequestException;
import ajude.psoft.projeto.servicos.ServicoCampanhas;
import ajude.psoft.projeto.servicos.ServicoUsuarios;

@RestController
public class ControladorUsuarios {

    @Autowired
    private ServicoUsuarios servicoUsuarios;
    @Autowired
    private ServicoCampanhas servicoCampanhas;

    // Adiciona um usuario com email, nome e senha. O email sera o login do usuario
    // e deve ser o identificador unico
    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> adicionaUsuario(@RequestBody Usuario novoUsuario){
        if (servicoUsuarios.existeNoDAO(novoUsuario.getEmail())){
            throw new ResourceBadRequestException("Email já cadastrado");
        }
        return new ResponseEntity<Usuario>(servicoUsuarios.adicionaUsuario(novoUsuario), HttpStatus.CREATED);
    }

    @GetMapping("/usuarios/{username}")
    public ResponseEntity<Usuario> retornaUsuarioPeloUsername(@PathVariable("username") String username){
        try {
            return new ResponseEntity<Usuario>(this.servicoUsuarios.retornaUsuario(username).get(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new ResourceBadRequestException("Este usuario não existe");
        }
        
    }

    @GetMapping("/usuarios/campanhas/criadas/{email}")
    public ResponseEntity<List<Campanha>> retornaCampanhasUsuario(@PathVariable ("email") String email){
        Usuario usuario;
        try {
            usuario = this.servicoUsuarios.retornaUsuario(email).get();
        } catch (NoSuchElementException e) {
            throw new ResourceBadRequestException("Este usuario não existe");
        }
        return new ResponseEntity<List<Campanha>>(this.servicoCampanhas.retornaCampanhasUsuario(usuario), HttpStatus.OK);
    }

    //as campanhas contribuicoes ficaram aqui

}