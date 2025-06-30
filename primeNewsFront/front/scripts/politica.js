async function carregarNoticias() {
  try {
    const response = await fetch('http://localhost:8080/noticias/politica');
    if (!response.ok) throw new Error('Erro ao buscar notícias');

    const noticias = await response.json();
    
    console.log('Notícias de política recebidas:', noticias);
    if (noticias.length > 0) {
      console.log('Primeira notícia de política:', noticias[0]);
      console.log('ID da primeira notícia de política:', noticias[0].id);
    }
    
    if (noticias.length === 0) return;

    const destaque = noticias[0];
    const destaqueSection = document.querySelector('.featured-news');
    
    if (!destaque.id) {
      console.error('ERRO: Campo "id" não encontrado na primeira notícia de política:', destaque);
      console.error('Campos disponíveis:', Object.keys(destaque));
      return;
    }
    
    destaqueSection.style.cursor = 'pointer';
    destaqueSection.onclick = () => {
      console.log('Clicou na notícia em destaque (política), ID:', destaque.id);
      window.location.href = `noticia.html?id=${destaque.id}`;
    };
    
    destaqueSection.querySelector('img').src = destaque.urlImagem || 'https://via.placeholder.com/800x400';
    destaqueSection.querySelector('img').alt = destaque.titulo;
    destaqueSection.querySelector('h3').textContent = destaque.titulo;
    destaqueSection.querySelector('p').textContent = destaque.descricao;

    const newsGrid = document.querySelector('.news-grid');
    newsGrid.innerHTML = '';
    noticias.slice(1).forEach((noticia, index) => {
      console.log(`Notícia de política ${index + 1}:`, noticia);
      console.log(`ID da notícia de política ${index + 1}:`, noticia.id);
      

      if (!noticia.id) {
        console.error(`ERRO: Campo "id" não encontrado na notícia de política ${index + 1}:`, noticia);
        console.error('Campos disponíveis:', Object.keys(noticia));
        return; // Pular esta notícia
      }
      
      const artigo = document.createElement('article');
      artigo.classList.add('news-card');
      artigo.style.cursor = 'pointer';

      artigo.innerHTML = `
        <img src="${noticia.urlImagem || 'https://via.placeholder.com/400x200'}" alt="Imagem da notícia">
        <div class="news-card-content">
          <h3>${noticia.titulo}</h3>
          <p>${noticia.descricao}</p>
        </div>`;
      

      artigo.onclick = () => {
        console.log('Clicou na notícia do grid (política), ID:', noticia.id);
        window.location.href = `noticia.html?id=${noticia.id}`;
      };
      
      newsGrid.appendChild(artigo);
    });

  } catch (error) {
    console.error('Erro ao carregar notícias:', error);
  }
}

window.addEventListener('DOMContentLoaded', carregarNoticias);
