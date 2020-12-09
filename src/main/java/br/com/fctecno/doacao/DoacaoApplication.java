package br.com.fctecno.doacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import br.com.fctecno.doacao.domain.model.File;

@EnableConfigurationProperties({
	File.class
})
@SpringBootApplication
@EnableAutoConfiguration
public class DoacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoacaoApplication.class, args);
	}

}
