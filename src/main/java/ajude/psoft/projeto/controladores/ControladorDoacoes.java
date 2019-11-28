package ajude.psoft.projeto.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ajude.psoft.projeto.entidades.Campanha;
import ajude.psoft.projeto.entidades.Doacao;
import ajude.psoft.projeto.entidades.DoacaoDTO;
import ajude.psoft.projeto.entidades.Usuario;
import ajude.psoft.projeto.servicos.ServicoCampanhas;
import ajude.psoft.projeto.servicos.ServicoDoacoes;
import ajude.psoft.projeto.servicos.ServicoUsuarios;
import io.swagger.annotations.ApiOperation;

/**
 * Controlador que administra as rotas que envolvem as doações, consegue concluir os pedidos que são
 * feitos com a ajuda dos serviços que possui.
 * 
 * @author Mauricio Marques da Silva Monte e Natan Ataide de Souza.
 */
@RestController
public class ControladorDoacoes {

    //Inicializando os serviços jwt e de usuarios automaticamente.

    @Autowired
    private ServicoCampanhas servicoCampanhas;
    @Autowired
    private ServicoUsuarios servicoUsuarios;
    @Autowired
    private ServicoDoacoes servicoDoacoes;

    /**
     * Rota de PostMapping que realiza uma doação em uma campanha através do body passado. É necessário
     * autenticação de login para acessar essa rota.
     *
     * @return ResponseEntity<Campanha> entidade de resposta que representa a campanha que recebeu a doação
     */
    @PostMapping("/doacoes")
    @ApiOperation(value="Retorna a campanha que recebeu uma doação")
    public ResponseEntity<Campanha> realizaDoacao(@RequestBody DoacaoDTO doacaoDTO){
        Doacao doacaoFinal = doacaoDTO.transformaParaDoacao();
        Usuario usuario = this.servicoUsuarios.retornaUsuario(doacaoDTO.getEmailDono()).get();
        Campanha campanha = this.servicoCampanhas.retornaCampanha(doacaoDTO.getIdCampanha()).get();
        doacaoFinal.setCampanha(campanha);
        doacaoFinal.setUsuario(usuario);
        return new ResponseEntity<Campanha>(this.servicoDoacoes.realizaDoacao(doacaoFinal), HttpStatus.OK);
    }

}