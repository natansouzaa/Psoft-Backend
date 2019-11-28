package ajude.psoft.projeto.daos;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ajude.psoft.projeto.entidades.Doacao;

/**
 * Repositório que armazena todas as doações que foram e as que serão realizadas na API.
 * 
 * @author Mauricio Marques da Silva Monte e Natan Ataide de Souza.
 */
@Repository
public interface RepositorioDoacoes<T, ID extends Serializable> extends JpaRepository<Doacao, Long>{

}