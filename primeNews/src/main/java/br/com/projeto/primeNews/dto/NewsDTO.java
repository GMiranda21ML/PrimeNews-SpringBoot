package br.com.projeto.primeNews.dto;

public record NewsDTO(Long id,
                      String titulo,
                      String descricao,
                      String urlImagem) {
}
