package ajude.psoft.projeto.servicos;

import java.rmi.ServerException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ajude.psoft.projeto.daos.RepositorioUsuarios;
import ajude.psoft.projeto.entidades.Usuario;

@Service
public class ServicoUsuarios {

    private RepositorioUsuarios<Usuario, String> usuariosDAO;

    public ServicoUsuarios(RepositorioUsuarios<Usuario, String> usuariosDAO){
        super();
        this.usuariosDAO = usuariosDAO;
    }

    public Usuario adicionaUsuario(Usuario usuario) throws ServerException{
        if (this.existeNoDAO(usuario.getEmail())){
            throw new ServerException("Email já cadastrado");
        }
        return usuariosDAO.save(usuario);
    }

    public boolean existeNoDAO(String email){
        return this.usuariosDAO.existsById(email);
    }

    public Optional<Usuario> retornaUsuario(String email){
        return this.usuariosDAO.findById(email);
    }

}