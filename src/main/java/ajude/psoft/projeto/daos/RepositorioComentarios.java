package ajude.psoft.projeto.daos;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ajude.psoft.projeto.entidades.Comentario;

/**
 * Repositório que armazena todos as comentários que foram e que serão adicionados na API.
 * 
 * @author Mauricio Marques da Silva Monte e Natan Ataide de Souza.
 */
@Repository
public interface RepositorioComentarios<T, ID extends Serializable> extends JpaRepository<Comentario, Long> {

}
