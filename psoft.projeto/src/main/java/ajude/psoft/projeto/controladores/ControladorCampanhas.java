package ajude.psoft.projeto.controladores;

import java.util.List;

import ajude.psoft.projeto.entidades.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ajude.psoft.projeto.erros.ResourceBadRequestException;
import ajude.psoft.projeto.servicos.ServicoCampanhas;
import ajude.psoft.projeto.servicos.ServicoUsuarios;

@RestController
public class ControladorCampanhas {

    @Autowired
    private ServicoCampanhas servicoCampanhas;
    @Autowired
    private ServicoUsuarios servicoUsuarios;

    // Adiciona uma campanha com id, nome curto, URL único da campanha(gerado pelo frontend a partir do nome curto)
    //descrição, deadline (data) para arrecadação, status da campanha, meta (reais), doações, usuário dono, comentários
    //feitos pelos usuários, likes dados pelos usuários.
    
    @PostMapping("/campanhas")
    public ResponseEntity<Campanha> adicionaCampanha(@RequestBody CampanhaDTO novaCampanhaDTO){
        Campanha campanhaFinal = novaCampanhaDTO.transformarParaCampanha();
        campanhaFinal.setUsuarioDono(servicoUsuarios.retornaUsuario(novaCampanhaDTO.getEmailDono()).get());
        if (servicoCampanhas.retornaCampanhaPeloIdentificadorURL(novaCampanhaDTO.getIdentificadorURL()) != null){
            throw new ResourceBadRequestException("URL já está em uso");
        }
        return new ResponseEntity<Campanha>(servicoCampanhas.adicionaCampanha(campanhaFinal), HttpStatus.CREATED);
    }

    @GetMapping("/campanhas/{identificadorURL}")
    public ResponseEntity<Campanha> retornaCampanhaPeloIdentificadorURL(@PathVariable("identificadorURL") String identificadorURL){
        if (servicoCampanhas.retornaCampanhaPeloIdentificadorURL(identificadorURL) == null){
            throw new ResourceBadRequestException("URL inválida");
        }
        return new ResponseEntity<Campanha>(servicoCampanhas.retornaCampanhaPeloIdentificadorURL(identificadorURL), HttpStatus.ACCEPTED);
    }    

    @GetMapping("/campanhas/pesquisa/{busca}")
    public ResponseEntity<List<Campanha>> retornaCampanhasPelaBusca(@PathVariable("busca") String busca, @RequestParam(value="todos", required = false) Boolean todos){
        if (servicoCampanhas.retornaCampanhasPelaBusca(busca, todos).size() == 0){
            throw new ResourceBadRequestException("Nenhum resultado encontrado");
        }
        return new ResponseEntity<List<Campanha>>(servicoCampanhas.retornaCampanhasPelaBusca(busca, todos), HttpStatus.ACCEPTED);
    }

}