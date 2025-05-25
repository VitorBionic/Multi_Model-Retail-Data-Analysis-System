
    fetch("https://quetzal-novel-man.ngrok-free.app/api/temporal/stock", {
    method: "POST",
    headers: {
    "ngrok-skip-browser-warning": "skip",
    "Content-Type": "application/json"
    },
    body: JSON.stringify({
        "filmId": 2,
        "quantity": 78,
        "startValidity": "2012-04-23T18:25:43.511Z",
        "endValidity": "2019-04-23T18:25:43.511Z"
    })
})
    .then(response => response.json())
    .then(data => {
        console.log(data)
    })
    .catch(error => {
        console.log("Error na requisição:", error);
    });