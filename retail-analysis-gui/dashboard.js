fetch("https://quetzal-novel-man.ngrok-free.app/api/odb/film", {
    headers: {
        "ngrok-skip-browser-warning": "skip",
    },
})
.then(response => response.json())
.then(filmes => {

    function formatarReais(valor) {
        return valor.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
    }

    function preencherKPIs() {
        const totalFilmes = filmes.length;
        const mediaDuracao = (filmes.reduce((acc, f) => acc + f.duration, 0) / totalFilmes).toFixed(1);
        const precoMedio = (filmes.reduce((acc, f) => acc + f.currentPrice, 0) / totalFilmes);
        const filmeRecente = filmes.reduce((prev, curr) => {
            return curr.releaseYear > prev.releaseYear ? curr : prev;
        }, filmes[0]);

        document.getElementById('kpiTotalFilmes').textContent = totalFilmes;
        document.getElementById('kpiMediaDuracao').textContent = `${mediaDuracao} min`;
        document.getElementById('kpiPrecoMedio').textContent = formatarReais(precoMedio);
        document.getElementById('kpiFilmeRecente').textContent = `${filmeRecente.title} (${filmeRecente.releaseYear})`;
    }

    function gerarRelatorioPorGenero() {
        const tbody = document.querySelector('#relatorioPorGenero tbody');
        tbody.innerHTML = '';

        const generoMap = {};
        filmes.forEach(f => {
            if (!generoMap[f.genre]) {
                generoMap[f.genre] = { count: 0, duracaoTotal: 0, precoTotal: 0 };
            }
            generoMap[f.genre].count++;
            generoMap[f.genre].duracaoTotal += f.duration;
            generoMap[f.genre].precoTotal += f.currentPrice;
        });

        for (const genero in generoMap) {
            const info = generoMap[genero];
            const mediaDuracao = (info.duracaoTotal / info.count).toFixed(1);
            const mediaPreco = (info.precoTotal / info.count).toFixed(2);

            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${genero}</td>
                <td>${info.count}</td>
                <td>${mediaDuracao} min</td>
                <td>${formatarReais(parseFloat(mediaPreco))}</td>
            `;
            tbody.appendChild(tr);
        }
    }

    
    preencherKPIs();
    gerarRelatorioPorGenero();
})
.catch(error => {
    console.error("Erro ao carregar os dados:", error);
});
