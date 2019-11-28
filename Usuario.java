package ajude.psoft.projeto.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Classe que tem a função de ser a entidade usuário que armazena varios dados do usuário.
 * 
 * @author Mauricio Marques da Silva Monte e Natan Ataide de Souza.
 */
@Entity
public class Usuario {

    @Id
    private String email;
    private String primeiroNome;
    private String ultimoNome;
    private String cartaoDeCredito;
    private String senha;

    /**
    * Constrói um usuário vazio.
	*/
    public Usuario(){
        super();
    }

    /**
    * Constrói um usuário a partir dos dados passados como paramêtro.
	* 
	* @param primeiroNome primeiro nome do usuário
    * @param ultimoNome último nome do usuário
    * @param email email do usuário que serve como identificador único
    * @param cartaoDeCredito cartão de crédito do usuário
    * @param senha senha do usuário
	*/
    public Usuario(String primeiroNome, String ultimoNome, String email, String cartaoDeCredito, String senha){
        super();
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
        this.email = email;
        this.cartaoDeCredito = cartaoDeCredito;
        this.senha = senha;
    }

    /**
    * getters e setters.
    */
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrimeiroNome() {
        return this.primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getUltimoNome() {
        return this.ultimoNome;
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    public String getCartaoDeCredito() {
        return this.cartaoDeCredito;
    }

    public void setCartaoDeCredito(String cartaoDeCredito) {
        this.cartaoDeCredito = cartaoDeCredito;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}