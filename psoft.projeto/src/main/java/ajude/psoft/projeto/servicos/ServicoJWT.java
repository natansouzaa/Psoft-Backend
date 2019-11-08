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

@Service
public class ServicoJWT {
	private ServicoUsuarios usuariosService;
	private final String TOKEN_KEY = "login";

	public ServicoJWT(ServicoUsuarios usuariosService) {
		super();
		this.usuariosService = usuariosService;
	}

	public boolean usuarioExiste(String authorizationHeader) throws ServletException {
		String subject = getSujeitoDoToken(authorizationHeader);

		return usuariosService.retornaUsuario(subject).isPresent();
    }

    public boolean usuarioTemPermissao(String authorizationHeader, String email) throws ServletException {
        String subject = getSujeitoDoToken(authorizationHeader);

        Optional<Usuario> optUsuario = usuariosService.retornaUsuario(subject);
		return optUsuario.isPresent() && optUsuario.get().getEmail().equals(email);
	}

	private String getSujeitoDoToken(String authorizationHeader){
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

	public String geraToken(String email) {
		return Jwts.builder().setSubject(email).signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
				.setExpiration(new Date(System.currentTimeMillis() + 3 * 60 * 1000)).compact();// 3 min
	}

}