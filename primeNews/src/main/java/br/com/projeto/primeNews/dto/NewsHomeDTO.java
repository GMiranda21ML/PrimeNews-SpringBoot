package br.com.projeto.primeNews.dto;

import jakarta.persistence.Column;

import java.time.LocalDate;

public record NewsHomeDTO(String titulo,
                          String descricao,
                          String urlImagem) {
}
