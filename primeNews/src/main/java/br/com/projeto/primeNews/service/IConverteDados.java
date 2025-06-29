package br.com.projeto.primeNews.service;

import java.util.List;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
    <T> List<T> obterDadosLista(String json, Class<T> classe);
}
