package br.com.projeto.primeNews.service;

import br.com.projeto.primeNews.dto.NewsHomeDTO;
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

    public List<NewsHomeDTO> obterDadosHome() {
        return newsRepository.findTop5By().stream()
                .map(n -> new NewsHomeDTO(n.getTitulo(), n.getDescricao(), n.getUrlImagem()))
                .collect(Collectors.toList());
    }
}
