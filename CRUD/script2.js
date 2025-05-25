let tabela4 = document.querySelector("#tabelaWareClientes thead") 
let tabela5 = document.querySelector("#tabelaWareFilmes thead") 
let tabela6 = document.querySelector("#tabelaWareLoja thead") 
let tabela7 = document.querySelector("#tabelaWareTempo thead") 
let tabela8 = document.querySelector("#tabelaWareLocacao thead") 

fetch("https://quetzal-novel-man.ngrok-free.app/api/dw/dim/client", {
    headers: {
    "ngrok-skip-browser-warning": "skip",
    },
    })
    .then(response => response.json())
    .then(data => {
        console.log(data)
        for (let client of data){
            let linha = document.createElement("tr")
            tabela4.appendChild(linha)
            let tdid = document.createElement("td")
            tdid.innerText = client.id
            linha.appendChild(tdid)
            let tdnome = document.createElement("td")
            tdnome.innerText = client.name
            linha.appendChild(tdnome)
            let tdemail = document.createElement("td")
            tdemail.innerText = client.email
            linha.appendChild(tdemail)
            let tdcidade = document.createElement("td")
            tdcidade.innerText = client.city
            linha.appendChild(tdcidade)
            let tdestado = document.createElement("td")
            tdestado.innerText = client.state
            linha.appendChild(tdestado)
        }
    })
    .catch(error => {
        console.log("Error na requisição:", error);
    });

    fetch("https://quetzal-novel-man.ngrok-free.app/api/dw/dim/film", {
    headers: {
    "ngrok-skip-browser-warning": "skip",
    },
    })
    .then(response => response.json())
    .then(data => {
        console.log(data)
        for (let film of data){
            let linha = document.createElement("tr")
            tabela5.appendChild(linha)
            let tdid = document.createElement("td")
            tdid.innerText = film.id
            linha.appendChild(tdid)
            let tdtitulo = document.createElement("td")
            tdtitulo.innerText = film.title
            linha.appendChild(tdtitulo)
            let tdgenero = document.createElement("td")
            tdgenero.innerText = film.genre
            linha.appendChild(tdgenero)
            let tdduracao = document.createElement("td")
            tdduracao.innerText = film.duration
            linha.appendChild(tdduracao)
        }
    })
    .catch(error => {
        console.log("Error na requisição:", error);
    });

     fetch("https://quetzal-novel-man.ngrok-free.app/api/dw/dim/store", {
    headers: {
    "ngrok-skip-browser-warning": "skip",
    },
    })
    .then(response => response.json())
    .then(data => {
        console.log(data)
        for (let film of data){
            let linha = document.createElement("tr")
            tabela6.appendChild(linha)
            let tdid = document.createElement("td")
            tdid.innerText = film.id
            linha.appendChild(tdid)
            let tdnome = document.createElement("td")
            tdnome.innerText = film.name
            linha.appendChild(tdnome)
            let tdcidade = document.createElement("td")
            tdcidade.innerText = film.city
            linha.appendChild(tdcidade)
            let tdestado = document.createElement("td")
            tdestado.innerText = film.state
            linha.appendChild(tdestado)
        }
    })
    .catch(error => {
        console.log("Error na requisição:", error);
    });

      fetch("https://quetzal-novel-man.ngrok-free.app/api/dw/fact/rental", {
    headers: {
    "ngrok-skip-browser-warning": "skip",
    },
    })
    .then(response => response.json())
    .then(data => {
        console.log(data)
        for (let film of data){
            let linha = document.createElement("tr")
            tabela8.appendChild(linha)
            let tdid = document.createElement("td")
            tdid.innerText = film.id
            linha.appendChild(tdid)
            let tdfilmid = document.createElement("td")
            tdfilmid.innerText = film.filmId
            linha.appendChild(tdfilmid)
            let tdclientid = document.createElement("td")
            tdclientid.innerText = film.clientId
            linha.appendChild(tdclientid)
            let tdstoreid = document.createElement("td")
            tdstoreid.innerText = film.storeId
            linha.appendChild(tdstoreid)
            let tdpaidValue = document.createElement("td")
            tdpaidValue.innerText = film.paidValue
            linha.appendChild(tdpaidValue)
            let tdquantidade = document.createElement("td")
            tdquantidade.innerText = film.quantity
            linha.appendChild(tdquantidade)
        }
    })
    .catch(error => {
        console.log("Error na requisição:", error);
    });

