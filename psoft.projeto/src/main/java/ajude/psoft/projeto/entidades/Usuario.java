package ajude.psoft.projeto.entidades;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Usuario)) {
            return false;
        }
        Usuario usuario = (Usuario) o;
        return Objects.equals(email, usuario.email) && Objects.equals(primeiroNome, usuario.primeiroNome) && Objects.equals(ultimoNome, usuario.ultimoNome) && Objects.equals(cartaoDeCredito, usuario.cartaoDeCredito) && Objects.equals(senhaDoCartao, usuario.senhaDoCartao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, primeiroNome, ultimoNome, cartaoDeCredito, senhaDoCartao);
    }

}