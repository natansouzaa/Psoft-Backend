package ajude.psoft.projeto.controladores;

import java.util.Optional;

import javax.servlet.ServletException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ajude.psoft.projeto.entidades.Usuario;
import ajude.psoft.projeto.servicos.JWTService;
import ajude.psoft.projeto.servicos.ServicoUsuarios;

@RestController
@RequestMapping("/auth")
public class LoginController {

	private JWTService jwtService;
	private ServicoUsuarios usuariosService;

	public LoginController(ServicoUsuarios usuariosService, JWTService jwtService) {
		super();
		this.usuariosService = usuariosService;
		this.jwtService = jwtService;
	}

	@PostMapping("/login")
	public LoginResponse authenticate(@RequestBody Usuario usuario) throws ServletException {

		// Recupera o usuario
		Optional<Usuario> authUsuario = usuariosService.retornaUsuario(usuario.getEmail());

		// verificacoes
		if (!authUsuario.isPresent()) {
			throw new ServletException("Usuario nao encontrado!");
		}

		verificaSenha(usuario, authUsuario);

		String token = jwtService.geraToken(authUsuario.get().getEmail());

		return new LoginResponse(token);

	}

	private void verificaSenha(Usuario usuario, Optional<Usuario> authUsuario) throws ServletException {
		if (!authUsuario.get().getSenhaDoCartao().equals(usuario.getSenhaDoCartao())) {
			throw new ServletException("Senha invalida!");
		}
	}

	private class LoginResponse {
		public String token;

		public LoginResponse(String token) {
			this.token = token;

		}
	}
}