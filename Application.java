package ajude.psoft.projeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import ajude.psoft.projeto.filtros.FiltroToken;

/**
 * A classe Application é responsável por colocar e manter nossa API no ar, também
 * nos mantém informado dos erros que estão ocorrendo nas requisições do servidor.
 * 
 * @author Mauricio Marques da Silva Monte e Natan Ataide de Souza.
 */
@SpringBootApplication
public class Application {

	/**
	* Método que configura o filtro como um componente conhecido, o @Bean indica as rotas
	* que devem invocar o filtro. Ou seja, somente as rotas que de fato requerem token para acesso.
	*
	*@return FilterRegistrationBean<FiltroToken> filtro configurado
	*
	*/
	@Bean
	public FilterRegistrationBean<FiltroToken> filterJwt() {
		FilterRegistrationBean<FiltroToken> filterRB = new FilterRegistrationBean<FiltroToken>();
		filterRB.setFilter(new FiltroToken());
		filterRB.addUrlPatterns("/campanhas/*");
		filterRB.addUrlPatterns("/doacoes/*");
		return filterRB;
	}

	/**
	* Metodo que faz uma configuração específica de segurança para permitir que o frontend que está em um  
	* determinado domínio se cominique com o backend em outro domínio. Aqui habilitamos o Cross Origin
	* Resource Sharing(CORS).
	*
	*@return FilterRegistrationBean filtro que registou o Bean
	*
	*/
	@SuppressWarnings("rawtypes")
    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

	/**
	* Metodo responsável por inicializar e colocar a API no ar.
	*
	*/
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
