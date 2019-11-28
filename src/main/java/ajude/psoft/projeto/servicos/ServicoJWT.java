package ajude.psoft.projeto.servicos;

import java.util.Date;
import java.util.Optional;

import javax.servlet.ServletException;

import org.springframework.stereotype.Service;

import ajude.psoft.projeto.entidades.Usuario;
import ajude.psoft.projeto.erros.ResourceRequestTimeOutException;
import ajude.psoft.projeto.erros.ResourceUnauthorizedException;
import ajude.psoft.projeto.filtros.FiltroToken;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

/**
 * Servico que manipula toda e qualquer informação que envolvem tokens, aqui é onde
 * mantemos os dados dos tokens gerados pelos usuários, essencial para manter o funcionamento
 * privado da API.
 * 
 * @author Mauricio Marques da Silva Monte e Natan Ataide de Souza.
 */
@Service
public class ServicoJWT {
	private ServicoUsuarios usuariosService;
	private final String TOKEN_KEY = "login";

	/**
    * Constrói o servico de tokens a partir do servico de usuários, para que se obtenha a informação
    * e controle dos usuários que estão acessando a API.
	* 
	* @param usuariosDAO repositório dos usuários
	* @param servicoEmail servico de email
	*/
	public ServicoJWT(ServicoUsuarios usuariosService) {
		super();
		this.usuariosService = usuariosService;
	}

	/**
	* Método que faz a verificação se um determinado usuário está inserido no banco de dados.
    * 
    * @param authorizationHeader header que contém as informações de quem está acessando a API
	* @return boolean resultado da pesquisa, se o usuario existia na base de dados ou não
	*/
	public boolean usuarioExiste(String authorizationHeader) throws ServletException {
		String subject = getSujeitoDoToken(authorizationHeader);

		return usuariosService.retornaUsuario(subject).isPresent();
    }

	/**
	* Método que faz a verificação se um determinado usuário tem permissão para acessar o conteúdo.
    * 
	* @param authorizationHeader header que contém as informações de quem está acessando a API
	* @param email chave primaria do usuario a ser procurado
	* @return boolean resultado da pesquisa, se o usuario tem ou não permissão
	*/
    public boolean usuarioTemPermissao(String authorizationHeader, String email) throws ServletException {
        String subject = getSujeitoDoToken(authorizationHeader);

        Optional<Usuario> optUsuario = usuariosService.retornaUsuario(subject);
		return optUsuario.isPresent() && optUsuario.get().getEmail().equals(email);
	}

	/**
	* Método que pega o usuário a partir do header passado como paramêtro e verifica se seu token é valido.
    * 
    * @param authorizationHeader token passado que contém as informações de quem está acessando a API
	* @return String pode retornar alguma exceção se houver algum erro com o token, ou então retorna a permissão
	*/
	public String getSujeitoDoToken(String authorizationHeader){
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			throw new ResourceUnauthorizedException("Token inexistente ou mal formatado!");
		}

		// Extraindo apenas o token do cabecalho.
		String token = authorizationHeader.substring(FiltroToken.TOKEN_INDEX);

		String subject = null;
		try {
			subject = Jwts.parser().setSigningKey("login").parseClaimsJws(token).getBody().getSubject();
		} catch (SignatureException e) {
			throw new ResourceRequestTimeOutException("Token invalido ou expirado!");
		}
		return subject;
	}

	/**
	* Método responsável por gerar o token do usuário que está fazendo login na API, aqui definimos também
	* por quanto tempo o token será válido.
    * 
	* @param email chave primaria do usuario a ser procurado
	* @return String token gerado
	*/
	public String geraToken(String email) {
		return Jwts.builder().setSubject(email).signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
				.setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000)).compact();
	}

}