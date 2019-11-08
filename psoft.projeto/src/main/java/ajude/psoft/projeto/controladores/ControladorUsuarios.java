package ajude.psoft.projeto.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ajude.psoft.projeto.entidades.Usuario;
import ajude.psoft.projeto.erros.ResourceBadRequestException;
import ajude.psoft.projeto.servicos.ServicoUsuarios;

@CrossOrigin
@RestController
public class ControladorUsuarios {

    @Autowired
    private ServicoUsuarios servicoUsuarios;

    // Adiciona um usuario com email, nome e senha. O email sera o login do usuario
    // e deve ser o identificador unico
    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> adicionaUsuario(@RequestBody Usuario novoUsuario){
        if (servicoUsuarios.existeNoDAO(novoUsuario.getEmail())){
            throw new ResourceBadRequestException("Email já cadastrado");
        }
        return new ResponseEntity<Usuario>(servicoUsuarios.adicionaUsuario(novoUsuario), HttpStatus.CREATED);
    }

}