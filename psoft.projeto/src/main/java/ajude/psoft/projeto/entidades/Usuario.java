package ajude.psoft.projeto.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Usuario {

    @Id
    private String email;
    private String primeiroNome;
    private String ultimoNome;
    private String cartaoDeCredito;
    private String senhaDoCartao;

    public Usuario(){
        super();
    }

    public Usuario(String primeiroNome, String ultimoNome, String email, String cartaoDeCredito, String senhaDoCartao){
        super();
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
        this.email = email;
        this.cartaoDeCredito = cartaoDeCredito;
        this.senhaDoCartao = senhaDoCartao;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPrimeiroNome() {
        return this.primeiroNome;
    }

    public String getUltimoNome() {
        return this.ultimoNome;
    }

    public String getCartaoDeCredito() {
        return this.cartaoDeCredito;
    }

    public String getSenhaDoCartao() {
        return this.senhaDoCartao;
    }

}