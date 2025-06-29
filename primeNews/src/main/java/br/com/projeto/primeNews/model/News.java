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
    @Column(unique = true)
    private String titulo;
    @Column(length = 1000)
    private String descricao;
    private String url;
    @Column(length = 1000)
    private String urlImagem;
    private LocalDate dataPublicacao;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
//    @Lob
    private String conteudo; // se for usar o conteudo todo, colocar o Lob e atualizar o construtor

    public News(ArticleDados articleDados, Categoria categoria) {
        this.nome = articleDados.sourceDados().nome();
        this.autor = articleDados.autor();
        this.titulo = articleDados.titulo();
        this.descricao = articleDados.descricao();
        this.url = articleDados.url();
        this.urlImagem = articleDados.urlImagem();
        this.dataPublicacao = LocalDate.parse(articleDados.dataPublicacao().substring(0, 10));
        this.conteudo = articleDados.conteudo().substring(0, 101).trim() + "..."; // por enquanto so para testes
        this.categoria = categoria;
    }

    public News() {}

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
