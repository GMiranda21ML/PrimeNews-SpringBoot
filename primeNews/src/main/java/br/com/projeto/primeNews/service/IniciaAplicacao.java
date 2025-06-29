package br.com.projeto.primeNews.service;

import br.com.projeto.primeNews.model.ArticleDados;
import br.com.projeto.primeNews.model.Categoria;
import br.com.projeto.primeNews.model.News;
import br.com.projeto.primeNews.repository.NewsRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IniciaAplicacao {
    private final String ENDERECO = "https://newsapi.org/v2";
    private final String APIKEY = System.getenv("NEWS_APIKEY");
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDados converteDados = new ConverteDados();
    @Autowired
    private NewsRepository newsRepository;

    @PostConstruct
    public void startProject() {
        salvaDadosTudo();
        salvaDadosEconomia();
        salvaDadosEsportes();
        salvaDadosPolitica();
        salvaDadosTecnologia();

    }

    private List<ArticleDados> extrairJson(String endereco) {
        String json = consumoAPI.consumirAPI(ENDERECO + endereco + APIKEY);

        json = converteDados.extrairArticles(json);

        return converteDados.obterDadosLista(json, ArticleDados.class);
    }

    private void salvaDadosTudo() {
        List<ArticleDados> articleDados = extrairJson("/everything?language=pt&q=notícia&apiKey=");

        List<News> noticias = articleDados.stream()
                .map(n -> new News(n, Categoria.TUDO))
                .collect(Collectors.toList());

        for (News news : noticias) {
            if (!newsRepository.existsByTitulo(news.getTitulo())) {
                newsRepository.save(news);
            }
        }
    }

    private void salvaDadosEconomia() {
        List<ArticleDados> articleDados = extrairJson("/everything?language=pt&q=economia&apiKey=");

        List<News> noticias = articleDados.stream()
                .map(n -> new News(n, Categoria.ECONOMIA))
                .collect(Collectors.toList());

        for (News news : noticias) {
            if (!newsRepository.existsByTitulo(news.getTitulo())) {
                newsRepository.save(news);
            }
        }
    }

    private void salvaDadosEsportes() {
        List<ArticleDados> articleDados = extrairJson("/everything?language=pt&q=esportes&apiKey=");

        List<News> noticias = articleDados.stream()
                .map(n -> new News(n, Categoria.ESPORTES))
                .collect(Collectors.toList());

        for (News news : noticias) {
            if (!newsRepository.existsByTitulo(news.getTitulo())) {
                newsRepository.save(news);
            }
        }
    }

    private void salvaDadosPolitica() {
        List<ArticleDados> articleDados = extrairJson("/everything?language=pt&q=política&apiKey=");

        List<News> noticias = articleDados.stream()
                .map(n -> new News(n, Categoria.POLITICA))
                .collect(Collectors.toList());

        for (News news : noticias) {
            if (!newsRepository.existsByTitulo(news.getTitulo())) {
                newsRepository.save(news);
            }
        }
    }

    private void salvaDadosTecnologia() {
        List<ArticleDados> articleDados = extrairJson("/everything?language=pt&q=tecnologia&apiKey=");

        List<News> noticias = articleDados.stream()
                .map(n -> new News(n, Categoria.TECNOLOGIA))
                .collect(Collectors.toList());

        for (News news : noticias) {
            if (!newsRepository.existsByTitulo(news.getTitulo())) {
                newsRepository.save(news);
            }
        }
    }
}
