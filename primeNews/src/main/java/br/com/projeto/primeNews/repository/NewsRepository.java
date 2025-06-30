package br.com.projeto.primeNews.repository;

import br.com.projeto.primeNews.model.Categoria;
import br.com.projeto.primeNews.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    boolean existsByTitulo(String titulo);

    @Query("SELECT n FROM News n WHERE n.categoria = :categoria ORDER BY dataPublicacao DESC LIMIT 5")
    List<News> top5NoticiasTudo(Categoria categoria);

    @Query("Select n FROM News n WHERE n.categoria = :categoria ORDER BY dataPublicacao DESC")
    List<News> noticiasDePolitica(Categoria categoria);



}
