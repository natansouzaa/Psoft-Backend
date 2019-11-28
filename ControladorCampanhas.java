package ajude.psoft.projeto.controladores;

import java.util.List;

import ajude.psoft.projeto.entidades.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ajude.psoft.projeto.erros.ResourceBadRequestException;
import ajude.psoft.projeto.servicos.ServicoCampanhas;
import ajude.psoft.projeto.servicos.ServicoJWT;
import ajude.psoft.projeto.servicos.ServicoUsuarios;

/**
 * Controlador que administra as rotas que envolvem as campanhas, consegue concluir os pedidos que são
 * feitos com a ajuda dos serviços que possui.
 * 
 * @author Mauricio Marques da Silva Monte e Natan Ataide de Souza.
 */
@RestController
public class ControladorCampanhas {

    //Inicializando os serviços jwt e de usuarios automaticamente.

    @Autowired
    private ServicoCampanhas servicoCampanhas;
    @Autowired
    private ServicoUsuarios servicoUsuarios;
    @Autowired
    private ServicoJWT jwtService;

    /**
     * Rota de PostMapping que adiciona uma campanha com nome curto, URL único da campanha(gerado pelo frontend a partir do nome curto)
     * descrição, deadline para arrecadação, status da campanha, meta (reais), doações, usuário dono, comentários
     * feitos pelos usuários, likes dados pelos usuários. Recebe uma campanhaDTO no body para auxiliar no cadastro do campanha.
     * É necessário autenticação de login para acessar essa rota.
     *
     * @return ResponseEntity<Campanha> entidade de resposta que representa uma campanha
     */
    
    @PostMapping("/campanhas")
    public ResponseEntity<Campanha> adicionaCampanha(@RequestBody CampanhaDTO novaCampanhaDTO){
        Campanha campanhaFinal = novaCampanhaDTO.transformarParaCampanha();
        campanhaFinal.setUsuarioDono(servicoUsuarios.retornaUsuario(novaCampanhaDTO.getEmailDono()).get());
        if (servicoCampanhas.retornaCampanhaPeloIdentificadorURL(novaCampanhaDTO.getIdentificadorURL()) != null){
            throw new ResourceBadRequestException("URL já está em uso");
        }
        return new ResponseEntity<Campanha>(servicoCampanhas.adicionaCampanha(campanhaFinal), HttpStatus.CREATED);
    }

    /**
     * Rota de GetMapping que retorna uma campanha a partir do seu identificadorURL que será passado como PathVariable na URL.
     * É necessário autenticação de login para acessar essa rota.
     *
     * @return ResponseEntity<Campanha> entidade de resposta que representa uma campanha
     */
    @GetMapping("/campanhas/{identificadorURL}")
    public ResponseEntity<Campanha> retornaCampanhaPeloIdentificadorURL(@PathVariable("identificadorURL") String identificadorURL){
        if (servicoCampanhas.retornaCampanhaPeloIdentificadorURL(identificadorURL) == null){
            throw new ResourceBadRequestException("URL inválida");
        }
        return new ResponseEntity<Campanha>(servicoCampanhas.retornaCampanhaPeloIdentificadorURL(identificadorURL), HttpStatus.ACCEPTED);
    }    

    /**
     * Rota de GetMapping que retorna uma lista de campanhas que contém no nome curto uma String passada como PathVariable na URL.
     * Retorna somente as campanha ativas por padrão, mas através do parâmetro todos o usuário da API pode alterar isso e retornar
     * campanhas que estejam em qualquer outro estado. É necessário autenticação de login para acessar essa rota.
     *
     * @return ResponseEntity<List<Campanha>> entidade de resposta que representa a lista de campanhas que atendem a filtragem
     */
    @GetMapping("/campanhas/pesquisa/{busca}")
    public ResponseEntity<List<Campanha>> retornaCampanhasPelaBusca(@PathVariable("busca") String busca, @RequestParam(value="todos", required = false) Boolean todos){
        if (servicoCampanhas.retornaCampanhasPelaBusca(busca, todos).size() == 0){
            throw new ResourceBadRequestException("Nenhum resultado encontrado");
        }
        return new ResponseEntity<List<Campanha>>(servicoCampanhas.retornaCampanhasPelaBusca(busca, todos), HttpStatus.ACCEPTED);
    }

    /**
     * Rota de PostMapping que pode dar ou retirar a curtida de uma campanha, esta campanha é passada através de um PathVariable
     * na URL. O usuário que está dando o curtida consegue ser capturado graças ao header authorization.
     * É necessário autenticação de login para acessar essa rota.
     *
     * @return ResponseEntity<Campanha> entidade de resposta que representa a campanha que recebeu ou perdeu uma curtida
     */
    @PostMapping("/campanhas/curtida/{id}")
    public ResponseEntity<Campanha> relacaoCurtida(@PathVariable ("id") long id, @RequestHeader("Authorization") String header){
        String email = this.jwtService.getSujeitoDoToken(header);
        Usuario usuario = this.servicoUsuarios.retornaUsuario(email).get();
        Campanha campanha = this.servicoCampanhas.retornaCampanha(id).get();
        return new ResponseEntity<Campanha>(this.servicoCampanhas.relacaoCurtida(campanha, usuario), HttpStatus.ACCEPTED);
    }

    /**
     * Rota de PutMapping que pode editar uma campanha já existente no banco de dados, para isso é necessário receber no
     * body uma entidade EditaCampanha para fazer alterações na campanha desejada. É necessário autenticação de login para acessar essa rota.
     *
     * @return ResponseEntity<Campanha> entidade de resposta que representa a campanha que foi alterada
     */
    @PutMapping("/campanhas/edicao")
    public ResponseEntity<Campanha> editaCampanha(@RequestBody EditaCampanha editaCampanha){
        Campanha campanha = this.servicoCampanhas.retornaCampanhaPeloIdentificadorURL(editaCampanha.getIdentificadorURL());
        if (campanha == null){
            throw new ResourceBadRequestException("URL inválida");
        }
        return new ResponseEntity<Campanha>(this.servicoCampanhas.editaCampanha(campanha, editaCampanha.getNovaDescricao()), HttpStatus.OK);
    }

    /**
     * Rota de GetMapping que retorna as 5 campanhas que estão mais próximas de bater a meta, também retorna as que já
     * alcançaram a meta mas ainda não foram encerradas.
     *
     * @return ResponseEntity<List<Campanha>> entidade de resposta que representa as campanhas que foram selecionadas
     */
    @GetMapping("/ordenacao/meta")
    public ResponseEntity<List<Campanha>> retornaCampanhasPelaMeta(){
        return new ResponseEntity<List<Campanha>>(servicoCampanhas.retornaCampanhasPelaMeta(), HttpStatus.OK);
    }

    /**
     * Rota de GetMapping que retorna as 5 campanhas que estão mais próximas de alcançar o deadline.
     *
     * @return ResponseEntity<List<Campanha>> entidade de resposta que representa as campanhas que foram selecionadas
     */
    @GetMapping("/ordenacao/data")
    public ResponseEntity<List<Campanha>> retornaCampanhasPelaData(){
        return new ResponseEntity<List<Campanha>>(servicoCampanhas.retornaCampanhasPelaData(), HttpStatus.OK);
    }

    /**
     * Rota de GetMapping que retorna as 5 campanhas que possuem o maior número de curtidas.
     *
     * @return ResponseEntity<List<Campanha>> entidade de resposta que representa as campanhas que foram selecionadas
     */
    @GetMapping("/ordenacao/curtida")
    public ResponseEntity<List<Campanha>> retornaCampanhasPelaCurtida(){
        return new ResponseEntity<List<Campanha>>(servicoCampanhas.retornaCampanhasPelaCurtida(), HttpStatus.OK);
    }

}