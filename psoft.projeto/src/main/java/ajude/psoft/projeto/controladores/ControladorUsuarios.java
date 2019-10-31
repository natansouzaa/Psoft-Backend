package ajude.psoft.projeto.controladores;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ajude.psoft.projeto.entidades.Usuario;
import ajude.psoft.projeto.envios.Expeditor;
import ajude.psoft.projeto.envios.Mensagem;
import ajude.psoft.projeto.servicos.ServicoUsuarios;

@RestController
public class ControladorUsuarios {

    @Autowired
    private ServicoUsuarios servicoUsuarios;

    //Adiciona um usuario com email, nome e senha. O email sera o login do usuario e deve ser um identificador unico
    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> adicionaUsuario(@RequestBody Usuario novoUsuario){
        if (servicoUsuarios.existeNoDAO(novoUsuario.getEmail())){
            return null;
        }

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				ControladorUsuarios.class.getPackage().getName());
		
		Expeditor expeditor = applicationContext.getBean(Expeditor.class);
		expeditor.enviar(new Mensagem("AquiJUntosDoandoEsperança <ajudeprojeto@gmail.com>", 
				Arrays.asList("Confirmação de Cadastro <" + novoUsuario.getEmail() + ">")
                , "Cadastro Confirmado!", "Olá, Seja Bem Vindo(a)! \n\n Seu cadastro foi realizado com sucesso, aproveite ao máximo a experiência " + 
                "de participar ativamente de nossos projetos. Para dar o primeiro passo e realizar sua primeira doação basta acessar o link: <aqui vai " +
                "ficar o link da aplicação> \n\nEste e-mail foi enviado altomaticamente pelo nosso sistema."));
		
		applicationContext.close();

        return new ResponseEntity<Usuario>(servicoUsuarios.adicionaUsuario(novoUsuario), HttpStatus.CREATED);
    }

}