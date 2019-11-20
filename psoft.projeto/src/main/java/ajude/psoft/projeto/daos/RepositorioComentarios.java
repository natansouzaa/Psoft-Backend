package ajude.psoft.projeto.daos;

import ajude.psoft.projeto.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface RepositorioComentarios<T, ID extends Serializable> extends JpaRepository<Comentario, Long> {

}
