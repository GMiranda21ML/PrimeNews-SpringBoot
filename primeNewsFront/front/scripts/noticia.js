
function getUrlParameter(name) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(name);
}

function formatarData(dataString) {
    if (!dataString) return 'Data não disponível';
    
    try {
        const data = new Date(dataString);
        return data.toLocaleDateString('pt-BR', {
            year: 'numeric',
            month: 'long',
            day: 'numeric',
            hour: '2-digit',
            minute: '2-digit'
        });
    } catch (error) {
        return 'Data não disponível';
    }
}

async function carregarNoticia() {
    const noticiaId = getUrlParameter('id');
    
    console.log('ID da notícia recebido:', noticiaId);
    
    if (!noticiaId || noticiaId === 'undefined') {
        document.body.innerHTML = `
            <div style="text-align: center; padding: 2rem; font-family: Arial, sans-serif;">
                <h1 style="color: #e74c3c;">Notícia não encontrada</h1>
                <p>ID da notícia não fornecido ou inválido.</p>
                <p>URL atual: ${window.location.href}</p>
                <a href="index.html" style="color: #3498db; text-decoration: none; font-weight: bold;">← Voltar para Home</a>
            </div>
        `;
        return;
    }

    try {
        console.log('Tentando buscar notícia com ID:', noticiaId);
        const response = await fetch(`http://localhost:8080/noticias/${noticiaId}`);
        
        console.log('Status da resposta:', response.status);
        
        if (!response.ok) {
            if (response.status === 404) {
                throw new Error(`Notícia com ID ${noticiaId} não encontrada no servidor`);
            } else {
                throw new Error(`Erro do servidor: ${response.status} ${response.statusText}`);
            }
        }

        const noticia = await response.json();
        console.log('Notícia carregada:', noticia);
        
    
        window.noticiaUrl = noticia.url;
        console.log('URL da notícia armazenada:', window.noticiaUrl);
        
    
        document.title = `${noticia.titulo} - Prime News`;
        document.getElementById('titulo-noticia').textContent = noticia.titulo;
        document.getElementById('descricao-noticia').textContent = noticia.descricao;
        document.getElementById('imagem-noticia').src = noticia.urlImagem || 'https://via.placeholder.com/800x400';
        document.getElementById('imagem-noticia').alt = noticia.titulo;
        
    
        document.getElementById('data-noticia').textContent = formatarData(noticia.dataPublicacao);
        document.getElementById('autor-noticia').textContent = noticia.autor || 'Autor não disponível';
        document.getElementById('categoria').textContent = noticia.categoria || 'Notícia';

    
        const btnSaberMais = document.querySelector('.btn-saber-mais');
        if (!window.noticiaUrl) {
            btnSaberMais.style.display = 'none';
            console.log('URL da notícia não encontrada, botão "Saber mais..." ocultado');
        } else {
            btnSaberMais.style.display = 'flex';
            btnSaberMais.href = window.noticiaUrl;
            console.log('URL da notícia encontrada:', window.noticiaUrl);
        }

    
        await carregarNoticiasRelacionadas(noticiaId);

    } catch (error) {
        console.error('Erro ao carregar notícia:', error);
        document.body.innerHTML = `
            <div style="text-align: center; padding: 2rem; font-family: Arial, sans-serif;">
                <h1 style="color: #e74c3c;">Erro ao carregar notícia</h1>
                <p>Não foi possível carregar a notícia solicitada.</p>
                <p><strong>Detalhes do erro:</strong> ${error.message}</p>
                <p><strong>ID tentado:</strong> ${noticiaId}</p>
                <p><strong>URL:</strong> ${window.location.href}</p>
                <br>
                <a href="index.html" style="color: #3498db; text-decoration: none; font-weight: bold;">← Voltar para Home</a>
            </div>
        `;
    }
}

async function carregarNoticiasRelacionadas(noticiaAtualId) {
    try {
        const response = await fetch('http://localhost:8080/noticias');
        
        if (!response.ok) {
            throw new Error('Erro ao buscar notícias relacionadas');
        }

        const todasNoticias = await response.json();
        
        const noticiasRelacionadas = todasNoticias
            .filter(noticia => noticia.id !== parseInt(noticiaAtualId))
            .slice(0, 5);

        const container = document.getElementById('noticias-relacionadas-container');
        
        if (noticiasRelacionadas.length === 0) {
            container.innerHTML = '<p style="color: #666;">Nenhuma notícia relacionada disponível.</p>';
            return;
        }

        container.innerHTML = '';

        noticiasRelacionadas.forEach(noticia => {
            const noticiaElement = document.createElement('div');
            noticiaElement.className = 'noticia-relacionada';
            noticiaElement.onclick = () => {
                window.location.href = `noticia.html?id=${noticia.id}`;
            };

            noticiaElement.innerHTML = `
                <h4>${noticia.titulo}</h4>
                <p>${noticia.descricao.substring(0, 100)}${noticia.descricao.length > 100 ? '...' : ''}</p>
            `;

            container.appendChild(noticiaElement);
        });

    } catch (error) {
        console.error('Erro ao carregar notícias relacionadas:', error);
        document.getElementById('noticias-relacionadas-container').innerHTML = 
            '<p style="color: #666;">Erro ao carregar notícias relacionadas.</p>';
    }
}

function adicionarEfeitosVisuais() {

    const noticiaContent = document.querySelector('.noticia-content');
    if (noticiaContent) {
        noticiaContent.style.opacity = '0';
        noticiaContent.style.transform = 'translateY(20px)';
        
        setTimeout(() => {
            noticiaContent.style.transition = 'all 0.6s ease-out';
            noticiaContent.style.opacity = '1';
            noticiaContent.style.transform = 'translateY(0)';
        }, 100);
    }


    const noticiasRelacionadas = document.querySelectorAll('.noticia-relacionada');
    noticiasRelacionadas.forEach((noticia, index) => {
        noticia.style.opacity = '0';
        noticia.style.transform = 'translateX(20px)';
        
        setTimeout(() => {
            noticia.style.transition = 'all 0.4s ease-out';
            noticia.style.opacity = '1';
            noticia.style.transform = 'translateX(0)';
        }, 200 + (index * 100));
    });
}

document.addEventListener('DOMContentLoaded', async () => {
    await carregarNoticia();
    setTimeout(adicionarEfeitosVisuais, 100);
});

window.addEventListener('popstate', carregarNoticia); 