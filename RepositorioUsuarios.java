package ajude.psoft.projeto.daos;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ajude.psoft.projeto.entidades.Usuario;

/**
 * Repositório que armazena todos os usuários que foram e os que serão cadastrados na API.
 * 
 * @author Mauricio Marques da Silva Monte e Natan Ataide de Souza.
 */
@Repository
public interface RepositorioUsuarios<T, ID extends Serializable> extends JpaRepository<Usuario, String>{

} 