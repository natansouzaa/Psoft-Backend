package ajude.psoft.projeto.erros;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
public class ResourceRequestTimeOutException extends RuntimeException{
    public ResourceRequestTimeOutException(String mensagem){
        super(mensagem);
    }
}