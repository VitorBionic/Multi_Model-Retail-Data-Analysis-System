let tabela13 = document.querySelector("#tabelaComentarios thead")
let tabela14 = document.querySelector("#tabelaImagens thead")

fetch("https://quetzal-novel-man.ngrok-free.app/api/mongo/comment", {
    headers: {
    "ngrok-skip-browser-warning": "skip",
    },
    })
    .then(response => response.json())
    .then(data => {
        console.log(data)
        for (let comment of data){
            let linha = document.createElement("tr")
            tabela13.appendChild(linha)
            let tdid = document.createElement("td")
            tdid.innerText = comment.id
            linha.appendChild(tdid)
            let tdfilmeId = document.createElement("td")
            tdfilmeId.innerText = comment.filmId
            linha.appendChild(tdfilmeId)
            let tdclienteId = document.createElement("td")
            tdclienteId.innerText = comment.clientId
            linha.appendChild(tdclienteId)
            let tdcomentario = document.createElement("td")
            tdcomentario.innerText = comment.comment
            linha.appendChild(tdcomentario)
            let tdavaliacao = document.createElement("td")
            tdavaliacao.innerText = comment.evaluation
            linha.appendChild(tdavaliacao)
            let tdcomentarioinst = document.createElement("td")
            tdcomentarioinst.innerText = comment.commentInstant
            linha.appendChild(tdcomentarioinst)
        }
    })
    .catch(error => {
        console.log("Error na requisição:", error);
    });

    fetch("https://quetzal-novel-man.ngrok-free.app/api/mongo/filmImage", {
        headers: {
        "ngrok-skip-browser-warning": "skip",
        },
        })
        .then(response => response.json())
        .then(data => {
            console.log(data)
            for (let yearAggregation of data){
                let linha = document.createElement("tr")
                tabela14.appendChild(linha)
                let tdid = document.createElement("td")
                tdid.innerText = yearAggregation.id
                linha.appendChild(tdid)
                let tdfilmeId = document.createElement("td")
                tdfilmeId.innerText = yearAggregation.filmId
                linha.appendChild(tdfilmeId)
                let tdimagemUrl = document.createElement("td")
                tdimagemUrl.innerText = yearAggregation.imageUrl
                linha.appendChild(tdimagemUrl)
                let tddescricao = document.createElement("td")
                tddescricao.innerText = yearAggregation.description
                linha.appendChild(tddescricao)
            }
        })
        .catch(error => {
            console.log("Error na requisição:", error);
        });
    