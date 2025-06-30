package br.com.projeto.primeNews.service;

import br.com.projeto.primeNews.dto.NewsDTO;
import br.com.projeto.primeNews.model.Categoria;
import br.com.projeto.primeNews.model.News;
import br.com.projeto.primeNews.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;

    private List<NewsDTO> listarNoticias(List<News> lista) {
        return lista.stream()
                .map(n -> new NewsDTO(n.getTitulo(), n.getDescricao(), n.getUrlImagem()))
                .collect(Collectors.toList());
    }

    public List<NewsDTO> obterDadosHome() {
        return listarNoticias(newsRepository.top5NoticiasTudo(Categoria.TUDO));
    }

    public List<NewsDTO> obterDadosPolitica() {
        return listarNoticias(newsRepository.noticiasDePolitica(Categoria.POLITICA));
    }
}
