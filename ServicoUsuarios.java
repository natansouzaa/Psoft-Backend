package ajude.psoft.projeto.servicos;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ajude.psoft.projeto.daos.RepositorioUsuarios;
import ajude.psoft.projeto.entidades.Usuario;

/**
 * Servico que Manipula toda e qualquer informação que envolve os usuários, aqui é onde
 * mantemos os dados que estão no repositório dos usuários, portanto este é o servico designado
 * para adicionar, remover e listar os usuários. Existe também uma ligação com o servico
 * email, para que sejamos capazes de enviar e-mail para os usuários da API.
 * 
 * @author Mauricio Marques da Silva Monte e Natan Ataide de Souza.
 */
@Service
public class ServicoUsuarios {

    private RepositorioUsuarios<Usuario, String> usuariosDAO;
    private ServicoEmail servicoEmail;

    /**
    * Constrói o servico de usuários a partir dos repositórios necessários para manter os
    * métodos aqui presentes em funcionamento.
	* 
	* @param usuariosDAO repositório dos usuários
	* @param servicoEmail servico de email
	*/
    public ServicoUsuarios(RepositorioUsuarios<Usuario, String> usuariosDAO, ServicoEmail servicoEmail){
        super();
        this.usuariosDAO = usuariosDAO;
        this.servicoEmail = servicoEmail;
    }

    /**
	* Método responsável por adicionar um novo usuário no banco de dados.
    * 
    * @param usuario usuario a ser adicionado
	* @return Usuario usuario que acabou de ser adicionado no banco de dados
	*/
    public Usuario adicionaUsuario(Usuario usuario){
        this.servicoEmail.sendNotification(usuario);
        return usuariosDAO.save(usuario);
    }

    /**
	* Método que faz a verificação se um determinado usuário está inserido no banco de dados.
    * 
    * @param email chave primaria do usuario a ser procurado
	* @return boolean resultado da pesquisa, se o usuario existia na base de dados ou não
	*/
    public boolean existeNoDAO(String email){
        return this.usuariosDAO.existsById(email);
    }

    /**
	* Método que retorna um determinado usuário que já existe no banco de dados. 
    * 
    * @param email chave primaria do usuario a ser retornado
	* @return Optional<Usuario> optional do usuario retornado
	*/
    public Optional<Usuario> retornaUsuario(String email){
        return this.usuariosDAO.findById(email);
    }

}