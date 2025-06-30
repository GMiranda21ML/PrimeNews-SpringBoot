package br.com.projeto.primeNews.dto;

import java.time.LocalDate;

public record NewsEspecificaDTO(Long id,
                                String autor,
                                String titulo,
                                String descricao,
                                String url,
                                String urlImagem,
                                LocalDate dataPublicacao) {
}
