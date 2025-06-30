package br.com.projeto.primeNews.repository;

import br.com.projeto.primeNews.model.Categoria;
import br.com.projeto.primeNews.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    boolean existsByTitulo(String titulo);

    @Query("SELECT n FROM News n WHERE n.categoria = :categoria ORDER BY dataPublicacao DESC LIMIT 5")
    List<News> top5Noticias(Categoria categoria);

    @Query("Select n FROM News n WHERE n.categoria = :categoria ORDER BY dataPublicacao DESC")
    List<News> noticiasDePolitica(Categoria categoria);

    @Query(
            value = "SELECT * FROM news " +
                    "WHERE titulo IS NOT NULL AND data_publicacao >= CURRENT_DATE - INTERVAL '2 months' " +
                    "ORDER BY RANDOM() LIMIT 5",
            nativeQuery = true
    )
    List<News> buscar5AleatoriasDosUltimos2Meses();

    @Query("SELECT n FROM News n WHERE n.categoria = :categoria ORDER BY dataPublicacao DESC LIMIT 8 OFFSET 5")
    List<News> ultimasNoticias(Categoria categoria);




}
