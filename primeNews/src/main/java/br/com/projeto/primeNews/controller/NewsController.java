package br.com.projeto.primeNews.controller;

import br.com.projeto.primeNews.dto.NewsHomeDTO;
import br.com.projeto.primeNews.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NewsController {
    @Autowired
    private NewsService newsService;

    @GetMapping("/noticias")
    public List<NewsHomeDTO> homePage() {
        return newsService.obterDadosHome();
    }
}
