package ajude.psoft.projeto.daos;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ajude.psoft.projeto.entidades.Curtida;

/**
 * Repositório que armazena todas as curtidas que foram e as que serão adicionadas na API.
 * 
 * @author Mauricio Marques da Silva Monte e Natan Ataide de Souza.
 */
@Repository
public interface RepositorioCurtidas<T, ID extends Serializable> extends JpaRepository<Curtida, Long>{

}