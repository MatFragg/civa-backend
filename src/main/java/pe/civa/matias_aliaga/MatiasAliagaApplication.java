package pe.civa.matias_aliaga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MatiasAliagaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatiasAliagaApplication.class, args);
	}

}
