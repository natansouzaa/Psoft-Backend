package ajude.psoft.projeto.erros;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ResourceUnauthorizedException extends RuntimeException {
    public ResourceUnauthorizedException(String mensagem){
        super(mensagem);
    }
}