package br.com.projeto.primeNews.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome; // é o nome do portal
    private String autor;
    private String titulo;
    private String descricao;
    private String url;
    private String urlImagem;
    private LocalDate dataPublicacao;
    private String conteudo;

    public News(ArticleDados articleDados) {
        this.nome = articleDados.sourceDados().nome();
        this.autor = articleDados.autor();
        this.titulo = articleDados.titulo();
        this.descricao = articleDados.descricao();
        this.url = articleDados.url();
        this.urlImagem = articleDados.urlImagem();
        this.dataPublicacao = LocalDate.parse(articleDados.dataPublicacao().substring(0, 10));
        this.conteudo = articleDados.conteudo();
    }

    public Long getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getAutor() {
        return this.autor;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public String getUrl() {
        return this.url;
    }

    public String getUrlImagem() {
        return this.urlImagem;
    }

    public LocalDate getDataPublicacao() {
        return this.dataPublicacao;
    }

    public String getConteudo() {
        return this.conteudo;
    }

    @Override
    public String toString() {
        return String.format("Portal: %s (%s)\nTitulo: %s\nDescrição: %s\n", this.nome, this.dataPublicacao, this.titulo, this.descricao);
    }
}
