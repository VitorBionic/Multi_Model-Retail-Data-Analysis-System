let tabelaAnomalias = document.querySelector("#tabelaAnomalias thead");
let tabelaPrevisao = document.querySelector("#tabelaPrevisaoVendas thead");

fetch("https://quetzal-novel-man.ngrok-free.app/api/datamining/anomalyDetection", {
    headers: {
        "ngrok-skip-browser-warning": "skip",
    },
})
.then(response => response.json())
.then(data => {
    console.log(data);
    for (let anomaly of data) {
        let linha = document.createElement("tr");
        tabelaAnomalias.appendChild(linha);

        let tdId = document.createElement("td");
        tdId.innerText = anomaly.id;
        linha.appendChild(tdId);

        let tdFilme = document.createElement("td");
        tdFilme.innerText = anomaly.film;
        linha.appendChild(tdFilme);

        let tdAno = document.createElement("td");
        tdAno.innerText = anomaly.year;
        linha.appendChild(tdAno);

        let tdMes = document.createElement("td");
        tdMes.innerText = anomaly.month;
        linha.appendChild(tdMes);

        let tdVendas = document.createElement("td");
        tdVendas.innerText = anomaly.sales;
        linha.appendChild(tdVendas);

        let tdStatus = document.createElement("td");
        tdStatus.innerText = anomaly.anomalyStatus;
        linha.appendChild(tdStatus);
    }
})
.catch(error => {
    console.log("Erro na requisição de Anomalias:", error);
});


fetch("https://quetzal-novel-man.ngrok-free.app/api/datamining/salesForecast", {
    headers: {
        "ngrok-skip-browser-warning": "skip",
    },
})
.then(response => response.json())
.then(data => {
    console.log(data);
    for (let forecast of data) {
        let linha = document.createElement("tr");
        tabelaPrevisao.appendChild(linha);

        let tdFilme = document.createElement("td");
        tdFilme.innerText = forecast.film;
        linha.appendChild(tdFilme);

        let tdAno = document.createElement("td");
        tdAno.innerText = forecast.year;
        linha.appendChild(tdAno);

        let tdMes = document.createElement("td");
        tdMes.innerText = forecast.month;
        linha.appendChild(tdMes);

        let tdVendasReais = document.createElement("td");
        tdVendasReais.innerText = forecast.actualSales;
        linha.appendChild(tdVendasReais);

        let tdVendasPrevistas = document.createElement("td");
        tdVendasPrevistas.innerText = forecast.forecastSales;
        linha.appendChild(tdVendasPrevistas);
    }
})
.catch(error => {
    console.log("Erro na requisição de Previsão de Vendas:", error);
});
