package ajude.psoft.projeto.entidades;

/**
 * Classe que possui uma representação mais simples de um usuário, que possui apenas e-mail e senha, serve
 * como Body que são passados como request, a API recebe esses dados e utiliza isso para fazer login de usuários.
 * 
 * @author Mauricio Marques da Silva Monte e Natan Ataide de Souza.
 */
public class UsuarioDTO {

    private String email;
    private String senha;


    /**
    * Constrói o UsuarioDTO a partir dos dados passados como parâmetro.
	* 
	* @param email email do usuario
	* @param senha senha do usuario
	*/
    public UsuarioDTO(String email, String senha) {
        this.email = email;
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

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}