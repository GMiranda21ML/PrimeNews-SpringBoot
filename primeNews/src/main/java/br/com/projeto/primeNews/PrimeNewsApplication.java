package br.com.projeto.primeNews;

import br.com.projeto.primeNews.main.Main;
import br.com.projeto.primeNews.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PrimeNewsApplication implements CommandLineRunner {

	@Autowired
	private NewsRepository newsRepository;


	public static void main(String[] args) {
		SpringApplication.run(PrimeNewsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(newsRepository);
		main.startProject();
	}
}
