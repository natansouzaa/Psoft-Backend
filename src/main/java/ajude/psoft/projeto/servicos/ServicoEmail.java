package ajude.psoft.projeto.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import ajude.psoft.projeto.entidades.Usuario;

/**
 * Servico que Manipula toda e qualquer informação quem envolvem e-mails, aqui é onde
 * mantemos os dados que serão enviados para os usuários da API, através de e-mails.
 * 
 * @author Mauricio Marques da Silva Monte e Natan Ataide de Souza.
 */
@Service
public class ServicoEmail {

	private JavaMailSender javaMailSender;

	@Autowired

	/**
    * Constrói o servico de e-mails, a partir da interface JavaMailSender.
	* 
	* @param javaMailSender interface que envia o e-mail para o usuário
	*/
	public ServicoEmail(JavaMailSender javaMailSender){
		this.javaMailSender = javaMailSender;
	}

	/**
    * Método responsável por construir a estrutura do e-mail e depois enviá-lo para o usuário.
	* 
	* @param usuario usuário que receberá o e-mail
	*/
	public void sendNotification(Usuario usuario) throws MailException{
		SimpleMailMessage mail = new SimpleMailMessage();
        
        mail.setTo(usuario.getEmail());
		mail.setFrom("ajudeprojeto@gmail.com");
		mail.setSubject("Cadastro Confirmado!");
		mail.setText("Olá, Seja Bem Vindo(a)!\n\nSeu cadastro foi realizado com sucesso, aproveite ao máximo a experiência " + 
		"de participar ativamente de nossos projetos. Para dar o primeiro passo e realizar sua primeira doação basta acessar o link: " +
		"projetoajude.netlify.com\n\nEste e-mail foi enviado automaticamente pelo nosso sistema.");

		this.javaMailSender.send(mail);
	}

}