let tabela3 = document.querySelector("#tabelaEstoque thead")
let tabela2 = document.querySelector("#tabelaPreco thead")
let tabela = document.querySelector("#tabelaFilmes thead") 





fetch("https://quetzal-novel-man.ngrok-free.app/api/odb/film", {
    headers: {
    "ngrok-skip-browser-warning": "skip",
    },
    })
    .then(response => response.json())
    .then(data => {
        // console.log(data)
        for (let film of data){
            let linha = document.createElement("tr")
            tabela.appendChild(linha)
            let tdid = document.createElement("td")
            tdid.innerText = film.id
            linha.appendChild(tdid)
            let tdtitulo = document.createElement("td")
            tdtitulo.innerText = film.title
            linha.appendChild(tdtitulo)
            let tddescricao = document.createElement("td")
            tddescricao.innerText = film.description
            linha.appendChild(tddescricao)
            let tddatadelancamento = document.createElement("td")
            tddatadelancamento.innerText = film.releaseYear
            linha.appendChild(tddatadelancamento)
            let tdgenero = document.createElement("td")
            tdgenero.innerText = film.genre
            linha.appendChild(tdgenero)
            let tdduracao = document.createElement("td")
            tdduracao.innerText = film.duration
            linha.appendChild(tdduracao)
            let tdprecoatual = document.createElement("td")
            tdprecoatual.innerText = film.currentPrice            
            linha.appendChild(tdprecoatual)
        }
    })
    .catch(error => {
        console.log("Error na requisição:", error);
    });

    fetch("https://quetzal-novel-man.ngrok-free.app/api/temporal/price", {
    headers: {
    "ngrok-skip-browser-warning": "skip",
    },
    })
    .then(response => response.json())
    .then(data => {
        console.log(data)
        for (let price of data){
            let linha = document.createElement("tr")
            tabela2.appendChild(linha)
            let tdid = document.createElement("td")
            tdid.innerText = price.id
            linha.appendChild(tdid)
            let tdfilmeId = document.createElement("td")
            tdfilmeId.innerText = price.filmId
            linha.appendChild(tdfilmeId)
            let tdpreco = document.createElement("td")
            tdpreco.innerText = price.price
            linha.appendChild(tdpreco)
            let tdcomecovalidade = document.createElement("td")
            tdcomecovalidade.innerText = price.startValidity
            linha.appendChild(tdcomecovalidade)
            let tdfimvalidade = document.createElement("td")
            tdfimvalidade.innerText = price.endValidity
            linha.appendChild(tdfimvalidade)
        }
    })
    .catch(error => {
        console.log("Error na requisição:", error);
    });

    fetch("https://quetzal-novel-man.ngrok-free.app/api/temporal/stock", {
    headers: {
    "ngrok-skip-browser-warning": "skip",
    },
    })
    .then(response => response.json())
    .then(data => {
        console.log(data)
        for (let stock of data){
            let linha = document.createElement("tr")
            tabela3.appendChild(linha)
            let tdid = document.createElement("td")
            tdid.innerText = stock.id
            linha.appendChild(tdid)
            let tdfilmeId = document.createElement("td")
            tdfilmeId.innerText = stock.filmId
            linha.appendChild(tdfilmeId)
            let tdquantidade = document.createElement("td")
            tdquantidade.innerText = stock.quantity
            linha.appendChild(tdquantidade)
            let tdcomecovalidade = document.createElement("td")
            tdcomecovalidade.innerText = stock.startValidity
            linha.appendChild(tdcomecovalidade)
            let tdfimvalidade = document.createElement("td")
            tdfimvalidade.innerText = stock.endValidity
            linha.appendChild(tdfimvalidade)
        }
    })
    .catch(error => {
        console.log("Error na requisição:", error);
    });









