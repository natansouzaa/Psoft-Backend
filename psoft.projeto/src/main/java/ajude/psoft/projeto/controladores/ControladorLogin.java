package ajude.psoft.projeto.controladores;

import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ajude.psoft.projeto.entidades.Usuario;
import ajude.psoft.projeto.erros.ResourceBadRequestException;
import ajude.psoft.projeto.servicos.ServicoJWT;
import ajude.psoft.projeto.servicos.ServicoUsuarios;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class ControladorLogin {

	private ServicoJWT jwtService;
	private ServicoUsuarios usuariosService;

	public ControladorLogin(ServicoUsuarios usuariosService, ServicoJWT jwtService) {
		super();
		this.usuariosService = usuariosService;
		this.jwtService = jwtService;
	}

	@PostMapping("/login")
	public LoginResponse authenticate(@RequestBody Usuario usuario){

		// Recupera o usuario
		Optional<Usuario> authUsuario = usuariosService.retornaUsuario(usuario.getEmail());

		// verificacoes
		if (!authUsuario.isPresent()) {
			throw new ResourceBadRequestException("Email e/ou senha incorreto(s))");
		}

		verificaSenha(usuario, authUsuario);

		String token = jwtService.geraToken(authUsuario.get().getEmail());

		return new LoginResponse(token);

	}

	private void verificaSenha(Usuario usuario, Optional<Usuario> authUsuario){
		if (!authUsuario.get().getSenha().equals(usuario.getSenha())) {
			throw new ResourceBadRequestException("Email e/ou senha incorreto(s))");
		}
	}

	private class LoginResponse {
		public String token;

		public LoginResponse(String token) {
			this.token = token;

		}
	}
}