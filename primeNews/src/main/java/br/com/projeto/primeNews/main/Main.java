package br.com.projeto.primeNews.main;

import br.com.projeto.primeNews.service.ConsumoAPI;

public class Main {
    private final String ENDERECO = "https://newsapi.org/v2";
    private final String APIKEY = System.getenv("NEWS_APIKEY");
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    
    public void startProject() {
        String json = consumoAPI.consumirAPI(ENDERECO + "/everything?language=pt&q=notícia&apiKey=" + APIKEY);
        System.out.println(json);

    }
}
