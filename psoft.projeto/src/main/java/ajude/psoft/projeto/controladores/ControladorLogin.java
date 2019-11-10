package ajude.psoft.projeto.controladores;

import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ajude.psoft.projeto.entidades.Usuario;
import ajude.psoft.projeto.entidades.UsuarioDTO;
import ajude.psoft.projeto.erros.ResourceBadRequestException;
import ajude.psoft.projeto.servicos.ServicoJWT;
import ajude.psoft.projeto.servicos.ServicoUsuarios;

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
	public LoginResponse authenticate(@RequestBody UsuarioDTO usuarioDTO){

		// Recupera o usuario
		Optional<Usuario> authUsuario = usuariosService.retornaUsuario(usuarioDTO.getEmail());

		// verificacoes
		if (!authUsuario.isPresent()) {
			throw new ResourceBadRequestException("Email e/ou senha incorreto(s))");
		}

		verificaSenha(usuarioDTO.getSenha(), authUsuario);

		String token = jwtService.geraToken(authUsuario.get().getEmail());

		return new LoginResponse(token);

	}

	private void verificaSenha(String senhaUsuarioDTO, Optional<Usuario> authUsuario){
		if (!authUsuario.get().getSenha().equals(senhaUsuarioDTO)) {
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