package ajude.psoft.projeto.erros;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe de erros do tipo ResourceBadRequestException, que extende a classe RuntimeException para
 * lançar erros do tipo correto.
 * 
 * @author Mauricio Marques da Silva Monte e Natan Ataide de Souza.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceBadRequestException extends RuntimeException{

    /**
    * Método que retorna um ResourceBadRequestException com uma mensagem personalizada.
    * 
    * @param mensagem mensagem personalizada desejada
    */
    public ResourceBadRequestException(String mensagem){
        super(mensagem);

    }
}