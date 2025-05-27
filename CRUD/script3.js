let tabela9 = document.querySelector("#tabelaDrilldownFilmes thead")
let tabela10 = document.querySelector("#tabelaRollupGenero thead")
let tabela11 = document.querySelector("#tabelaDrilldownMes thead")
let tabela12 = document.querySelector("#tabelaRollupAno thead")


fetch("https://quetzal-novel-man.ngrok-free.app/api/olap/drilldownToFilm", {
    headers: {
    "ngrok-skip-browser-warning": "skip",
    },
    })
    .then(response => response.json())
    .then(data => {
        console.log(data)
        for (let filmAggregation of data){
            let linha = document.createElement("tr")
            tabela9.appendChild(linha)
            let tdgenre = document.createElement("td")
            tdgenre.innerText = filmAggregation.genre
            linha.appendChild(tdgenre)
            let tdtitulo = document.createElement("td")
            tdtitulo.innerText = filmAggregation.title
            linha.appendChild(tdtitulo)
            let tdtotal = document.createElement("td")
            tdtotal.innerText = filmAggregation.total
            linha.appendChild(tdtotal)
        }
    })
    .catch(error => {
        console.log("Error na requisição:", error);
    });

    fetch("https://quetzal-novel-man.ngrok-free.app/api/olap/rollupByGenre", {
        headers: {
        "ngrok-skip-browser-warning": "skip",
        },
        })
        .then(response => response.json())
        .then(data => {
            console.log(data)
            for (let genreAggregation of data){
                let linha = document.createElement("tr")
                tabela10.appendChild(linha)
                let tdgenre = document.createElement("td")
                tdgenre.innerText = genreAggregation.genre
                linha.appendChild(tdgenre)
                let tdtotal = document.createElement("td")
                tdtotal.innerText = genreAggregation.total
                linha.appendChild(tdtotal)
            }
        })
        .catch(error => {
            console.log("Error na requisição:", error);
        });

        fetch("https://quetzal-novel-man.ngrok-free.app/api/olap/drilldownToMonth", {
            headers: {
            "ngrok-skip-browser-warning": "skip",
            },
            })
            .then(response => response.json())
            .then(data => {
                console.log(data)
                for (let monthAggregation of data){
                    let linha = document.createElement("tr")
                    tabela11.appendChild(linha)
                    let tdano = document.createElement("td")
                    tdano.innerText = monthAggregation.year
                    linha.appendChild(tdano)
                    let tdmes = document.createElement("td")
                    tdmes.innerText = monthAggregation.month
                    linha.appendChild(tdmes)
                    let tdtotal = document.createElement("td")
                    tdtotal.innerText = monthAggregation.total
                    linha.appendChild(tdtotal)
                }
            })
            .catch(error => {
                console.log("Error na requisição:", error);
            });


            fetch("https://quetzal-novel-man.ngrok-free.app/api/olap/rollupByYear", {
                headers: {
                "ngrok-skip-browser-warning": "skip",
                },
                })
                .then(response => response.json())
                .then(data => {
                    console.log(data)
                    for (let yearAggregation of data){
                        let linha = document.createElement("tr")
                        tabela12.appendChild(linha)
                        let tdano = document.createElement("td")
                        tdano.innerText = yearAggregation.year
                        linha.appendChild(tdano)
                        let tdtotal = document.createElement("td")
                        tdtotal.innerText = yearAggregation.total
                        linha.appendChild(tdtotal)
                    }
                })
                .catch(error => {
                    console.log("Error na requisição:", error);
                });
            