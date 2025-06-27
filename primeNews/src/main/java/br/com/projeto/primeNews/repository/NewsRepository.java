package br.com.projeto.primeNews.repository;

import br.com.projeto.primeNews.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
    boolean existsByTitulo(String titulo);
}
