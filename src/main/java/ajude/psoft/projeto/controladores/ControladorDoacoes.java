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

@RestController
public class ControladorDoacoes {

    @Autowired
    private ServicoCampanhas servicoCampanhas;
    @Autowired
    private ServicoUsuarios servicoUsuarios;
    @Autowired
    private ServicoDoacoes servicoDoacoes;

    @PostMapping("/doacoes")
    public ResponseEntity<Doacao> realizaDoacao(@RequestBody DoacaoDTO doacaoDTO){
        Doacao doacaoFinal = doacaoDTO.transformaParaCampanha();
        Usuario usuario = this.servicoUsuarios.retornaUsuario(doacaoDTO.getEmailDono()).get();
        Campanha campanha = this.servicoCampanhas.retornaCampanha(doacaoDTO.getIdCampanha()).get();
        doacaoFinal.setCampanha(campanha);
        doacaoFinal.setUsuario(usuario);
        return new ResponseEntity<Doacao>(this.servicoDoacoes.realizaDoacao(doacaoFinal), HttpStatus.OK);
    }

}