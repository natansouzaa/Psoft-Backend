package ajude.psoft.projeto.configuracoes;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource("classpath:email/email.properties")
public class ConfiguracaoEmail {

    @Autowired
    private Environment email;

    @Bean
    public JavaMailSender mailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(email.getProperty("mail.smtp.host"));
        mailSender.setPort(email.getProperty("mail.smtp.port", Integer.class));
        mailSender.setUsername(email.getProperty("mail.smtp.username"));
        mailSender.setPassword(email.getProperty("mail.smtp.password"));

        Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.connectiontimeout", 10000);
		
		mailSender.setJavaMailProperties(props);

        return mailSender;
    }

}