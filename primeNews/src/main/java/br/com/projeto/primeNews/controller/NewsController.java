package br.com.projeto.primeNews.controller;

import br.com.projeto.primeNews.dto.NewsDTO;
import br.com.projeto.primeNews.dto.NewsEspecificaDTO;
import br.com.projeto.primeNews.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/noticias")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @GetMapping
    public List<NewsDTO> homePage() {
        return newsService.obterDadosHome();
    }

    @GetMapping("/politica")
    public List<NewsDTO> buscaNoticiasPolitica() {
        return newsService.obterDadosPolitica();
    }

    @GetMapping("/{id}")
    public NewsEspecificaDTO exibeNoticia(@PathVariable Long id) {
        return newsService.obterDadoNoticia(id);
    }

    @GetMapping("/aleatorias")
    public List<NewsDTO> NewsAleatorias() {
        return newsService.aleatorias();
    }

    @GetMapping("/ultimasNoticias")
    public List<NewsDTO> ultimasNoticias() {
        return newsService.ultimasNoticias();
    }
}
