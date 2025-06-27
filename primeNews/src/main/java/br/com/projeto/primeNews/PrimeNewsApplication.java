package br.com.projeto.primeNews;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PrimeNewsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PrimeNewsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello World");
	}
}
