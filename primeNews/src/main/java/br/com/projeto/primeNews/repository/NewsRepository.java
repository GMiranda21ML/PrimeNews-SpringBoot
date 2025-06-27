package br.com.projeto.primeNews.repository;

import br.com.projeto.primeNews.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    boolean existsByTitulo(String titulo);

    List<News> findTop5By();
}
