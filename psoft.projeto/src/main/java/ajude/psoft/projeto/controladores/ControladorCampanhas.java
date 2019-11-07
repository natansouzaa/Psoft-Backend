package ajude.psoft.projeto.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ajude.psoft.projeto.entidades.Campanha;
import ajude.psoft.projeto.entidades.CampanhaDTO;
import ajude.psoft.projeto.servicos.ServicoCampanhas;
import ajude.psoft.projeto.servicos.ServicoUsuarios;

@CrossOrigin
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
        Campanha campanhaFinal = new Campanha();
        campanhaFinal.setDataLimite(novaCampanhaDTO.getDataLimite());
        campanhaFinal.setDescricao(novaCampanhaDTO.getDescricao());
        campanhaFinal.setCurtidas(0);
        campanhaFinal.setIdentificadorURL(novaCampanhaDTO.getIdentificadorURL());
        campanhaFinal.setMeta(novaCampanhaDTO.getMeta());
        campanhaFinal.setNomeCurto(novaCampanhaDTO.getNomeCurto());
        campanhaFinal.setUsuarioDono(servicoUsuarios.retornaUsuario(novaCampanhaDTO.getEmailDono()).get());
        return new ResponseEntity<Campanha>(servicoCampanhas.adicionaCampanha(campanhaFinal), HttpStatus.CREATED);
    }

}