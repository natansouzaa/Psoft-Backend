package ajude.psoft.projeto.erros;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe de erros do tipo ResourceUnauthorizedException, que extende a classe RuntimeException para
 * lançar erros do tipo correto.
 * 
 * @author Mauricio Marques da Silva Monte e Natan Ataide de Souza.
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ResourceUnauthorizedException extends RuntimeException {
    
    /**
    * Método que retorna um ResourceUnauthorizedException com uma mensagem personalizada.
    * 
    * @param mensagem mensagem personalizada desejada
    */
    public ResourceUnauthorizedException(String mensagem){
        super(mensagem);
    }

}