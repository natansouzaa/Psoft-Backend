package ajude.psoft.projeto.daos;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ajude.psoft.projeto.entidades.Campanha;

/**
 * Repositório que armazena todas as campanhas que foram e as que serão adicionadas na API.
 * 
 * @author Mauricio Marques da Silva Monte e Natan Ataide de Souza.
 */
@Repository
public interface RepositorioCampanhas<T, ID extends Serializable> extends JpaRepository<Campanha, Long>{

    /**
    * Método responsável por retornar uma campanha a partir da sua URL.
    * 
    * @param identificadorURL URL da campanha
    * @return Campanha campanha encontrada
	*/
    Campanha findByidentificadorURL(String identificadorURL);

}