package ajude.psoft.projeto.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import ajude.psoft.projeto.entidades.Usuario;

@Service
public class ServicoEmail {

	private JavaMailSender javaMailSender;

	@Autowired

	public ServicoEmail(JavaMailSender javaMailSender){
		this.javaMailSender = javaMailSender;
	}

	public void sendNotification(Usuario usuario) throws MailException{
		SimpleMailMessage mail = new SimpleMailMessage();
        
        mail.setTo(usuario.getEmail());
		mail.setFrom("ajudeprojeto@gmail.com");
		mail.setSubject("Cadastro Confirmado!");
		mail.setText("Olá, Seja Bem Vindo(a)!\n\nSeu cadastro foi realizado com sucesso, aproveite ao máximo a experiência " + 
		"de participar ativamente de nossos projetos. Para dar o primeiro passo e realizar sua primeira doação basta acessar o link: <aqui vai " +
		"ficar o link da aplicação>\n\nEste e-mail foi enviado altomaticamente pelo nosso sistema.");

		this.javaMailSender.send(mail);
	}

}