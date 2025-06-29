package br.com.projeto.primeNews.main;

import br.com.projeto.primeNews.model.ArticleDados;
import br.com.projeto.primeNews.model.Categoria;
import br.com.projeto.primeNews.model.News;
import br.com.projeto.primeNews.repository.NewsRepository;
import br.com.projeto.primeNews.service.ConsumoAPI;
import br.com.projeto.primeNews.service.ConverteDados;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private final String ENDERECO = "https://newsapi.org/v2";
    private final String APIKEY = System.getenv("NEWS_APIKEY");
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDados converteDados = new ConverteDados();
    private NewsRepository newsRepository;

    public Main(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public void startProject() {
        String json = consumoAPI.consumirAPI(ENDERECO + "/everything?language=pt&q=notícia&apiKey=" + APIKEY);
//        System.out.println(json);

        json = converteDados.extrairArticles(json);

        List<ArticleDados> articleDados = converteDados.obterDadosLista(json, ArticleDados.class);

        List<News> noticias = articleDados.stream()
                .map(n -> new News(n, Categoria.TUDO))
                .collect(Collectors.toList());

        for (News news : noticias) {
            if (!newsRepository.existsByTitulo(news.getTitulo())) {
                newsRepository.save(news);
            }
        }


    }
}
