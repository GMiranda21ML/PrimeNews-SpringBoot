package br.com.projeto.primeNews.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ArticleDados(@JsonAlias("source") SourceDados sourceDados,
                           @JsonAlias("author") String autor,
                           @JsonAlias("title") String titulo,
                           @JsonAlias("description") String descricao,
                           @JsonAlias("url") String url,
                           @JsonAlias("urlToImage") String urlImagem,
                           @JsonAlias("publishedAt") String dataPublicacao,
                           @JsonAlias("content") String conteudo) {
}
