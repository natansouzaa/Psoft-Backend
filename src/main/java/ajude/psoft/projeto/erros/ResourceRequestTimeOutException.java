package ajude.psoft.projeto.erros;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe de erros do tipo ResourceRequestTimeOutException, que extende a classe RuntimeException para
 * lançar erros do tipo correto.
 * 
 * @author Mauricio Marques da Silva Monte e Natan Ataide de Souza.
 */
@ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
public class ResourceRequestTimeOutException extends RuntimeException{

    /**
    * Método que retorna um ResourceRequestTimeOutException com uma mensagem personalizada.
    * 
    * @param mensagem mensagem personalizada desejada
    */
    public ResourceRequestTimeOutException(String mensagem){
        super(mensagem);
    }

}