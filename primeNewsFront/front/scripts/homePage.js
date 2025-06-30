async function carregarNoticias() {
  try {
    const response = await fetch('http://localhost:8080/noticias');
    if (!response.ok) throw new Error('Erro ao buscar notícias');

    const noticias = await response.json();
    
    console.log('Notícias recebidas:', noticias);
    if (noticias.length > 0) {
      console.log('Primeira notícia:', noticias[0]);
      console.log('ID da primeira notícia:', noticias[0].id);
    }

    if (noticias.length === 0) return;

    const destaque = noticias[0];
    const destaqueSection = document.querySelector('.featured-news');
    
    if (!destaque.id) {
      console.error('ERRO: Campo "id" não encontrado na primeira notícia:', destaque);
      console.error('Campos disponíveis:', Object.keys(destaque));
      return;
    }
    
    destaqueSection.style.cursor = 'pointer';
    destaqueSection.onclick = () => {
      console.log('Clicou na notícia em destaque, ID:', destaque.id);
      window.location.href = `noticia.html?id=${destaque.id}`;
    };
    
    destaqueSection.querySelector('img').src = destaque.urlImagem || 'https://via.placeholder.com/800x400';
    destaqueSection.querySelector('img').alt = destaque.titulo;
    destaqueSection.querySelector('h3').textContent = destaque.titulo;
    destaqueSection.querySelector('p').textContent = destaque.descricao;

    const newsGrid = document.querySelector('.news-grid');
    newsGrid.innerHTML = '';

    noticias.slice(1).forEach((noticia, index) => {
      console.log(`Notícia ${index + 1}:`, noticia);
      console.log(`ID da notícia ${index + 1}:`, noticia.id);
      
      if (!noticia.id) {
        console.error(`ERRO: Campo "id" não encontrado na notícia ${index + 1}:`, noticia);
        console.error('Campos disponíveis:', Object.keys(noticia));
        return;
      }
      
      const artigo = document.createElement('article');
      artigo.classList.add('news-card');
      artigo.style.cursor = 'pointer';

      artigo.innerHTML = `
        <img src="${noticia.urlImagem || 'https://via.placeholder.com/400x200'}" alt="Imagem da notícia">
        <div class="news-card-content">
          <h3>${noticia.titulo}</h3>
          <p>${noticia.descricao}</p>
        </div>
      `;

      artigo.onclick = () => {
        console.log('Clicou na notícia do grid, ID:', noticia.id);
        window.location.href = `noticia.html?id=${noticia.id}`;
      };

      newsGrid.appendChild(artigo);
    });

    await carregarUltimasNoticias();

  } catch (error) {
    console.error('Erro ao carregar notícias:', error);
  }
}

async function carregarUltimasNoticias() {
  try {
    const response = await fetch('http://localhost:8080/noticias/ultimasNoticias');
        
    if (!response.ok) {
        throw new Error('Erro ao buscar notícias relacionadas');
    }

    const todasNoticias = await response.json();

    const sidebar = document.querySelector('.sidebar ul');
    sidebar.innerHTML = '';

    const ultimasNoticias = todasNoticias.slice(1, 8);

    ultimasNoticias.forEach(noticia => {
      if (!noticia.id) {
        console.error('ERRO: Campo "id" não encontrado na notícia da sidebar:', noticia);
        return;
      }

      const li = document.createElement('li');
      li.style.cursor = 'pointer';
      li.style.transition = 'all 0.3s ease';

      li.innerHTML = `
        <h4>${noticia.titulo}</h4>
        <p>${noticia.descricao.substring(0, 80)}${noticia.descricao.length > 80 ? '...' : ''}</p>
      `;

      li.onclick = () => {
        console.log('Clicou na notícia da sidebar, ID:', noticia.id);
        window.location.href = `noticia.html?id=${noticia.id}`;
      };

      li.addEventListener('mouseenter', () => {
        li.style.backgroundColor = '#f8f9fa';
        li.style.paddingLeft = '0.5rem';
        li.style.borderRadius = '8px';
      });

      li.addEventListener('mouseleave', () => {
        li.style.backgroundColor = '';
        li.style.paddingLeft = '';
        li.style.borderRadius = '';
      });

      sidebar.appendChild(li);
    });

  } catch (error) {
    console.error('Erro ao carregar últimas notícias:', error);
  }
}

window.addEventListener('DOMContentLoaded', carregarNoticias);
