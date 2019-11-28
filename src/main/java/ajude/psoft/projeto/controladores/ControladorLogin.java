package ajude.psoft.projeto.controladores;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ajude.psoft.projeto.entidades.Usuario;
import ajude.psoft.projeto.entidades.UsuarioDTO;
import ajude.psoft.projeto.erros.ResourceBadRequestException;
import ajude.psoft.projeto.servicos.ServicoJWT;
import ajude.psoft.projeto.servicos.ServicoUsuarios;
import io.swagger.annotations.ApiOperation;

/**
 * Controlador que administra os logins dos usuários, consegue gerar tokens como respostas para que os
 * usuários possam navegar por rotas que exigem autorização.
 * 
 * @author Mauricio Marques da Silva Monte e Natan Ataide de Souza.
 */
@RestController
@RequestMapping("/auth")
public class ControladorLogin {

	//Inicializando os serviços jwt e de usuarios automaticamente. 

	@Autowired
	private ServicoJWT jwtService;
	@Autowired
	private ServicoUsuarios usuariosService;

	/** 
	 * Rota de PostMapping que gera autenticação para um usuário da API que fez login.
     *
	 * @param usuarioDTO usuario que efetuou login
     * @return LoginResponse token gerado
     */
	@PostMapping("/login")
	@ApiOperation(value="Retorna um token pro usuário")
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

	/**
    * Método que verifica se a senha passada pelo usuarioDTO é igual a senha do usuário que está cadastrado no banco de dados.
	* 
	* @param senhaUsuarioDTO senha do usuário passado pelo body 
	* @param authUsuario optional do usuário retornado pelo banco de dados
	*/
	private void verificaSenha(String senhaUsuarioDTO, Optional<Usuario> authUsuario){
		if (!authUsuario.get().getSenha().equals(senhaUsuarioDTO)) {
			throw new ResourceBadRequestException("Email e/ou senha incorreto(s))");
		}
	}

	/**
    * Classe responsável somente por guardar o valor do token.
	* 
	*/
	private class LoginResponse {
		public String token;

		/**
    	* Método que faz a atribuição ao token da classe.
		* 
		* @param token token que deve ser guardado
		*/
		public LoginResponse(String token) {
			this.token = token;

		}
	}
}