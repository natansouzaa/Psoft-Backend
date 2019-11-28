package ajude.psoft.projeto.filtros;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.PrematureJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * Classe que manipula informações que envolvem tokens, fazendo verificações se tokens são válidos, se
 * são do tipo correto, se não envia as devidas mensagens de erros.
 * 
 * @author Mauricio Marques da Silva Monte e Natan Ataide de Souza.
 */
public class FiltroToken extends GenericFilterBean {
    public final static int TOKEN_INDEX = 7;

    /**
	* Método que faz a checagem se existe um token e também se ele é válido.
    * 
    * @param request requisição feita
    * @param response response gerada
    * @param chain filtro utilizado
	*/
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String header = req.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Token inexistente ou mal formatado!");
            return;
        }

        String token = header.substring(TOKEN_INDEX);
        try {
            Jwts.parser().setSigningKey("login").parseClaimsJws(token).getBody();
        } catch (SignatureException | ExpiredJwtException | MalformedJwtException | PrematureJwtException
                | UnsupportedJwtException | IllegalArgumentException e) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;

        }
        chain.doFilter(request, response);
    }
}