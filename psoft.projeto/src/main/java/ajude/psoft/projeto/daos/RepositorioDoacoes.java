package ajude.psoft.projeto.daos;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ajude.psoft.projeto.entidades.Doacao;

@Repository
public interface RepositorioDoacoes<T, ID extends Serializable> extends JpaRepository<Doacao, Long>{

}