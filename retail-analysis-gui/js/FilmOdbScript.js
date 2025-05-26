const table = document.querySelector("table")

fetch("https://quetzal-novel-man.ngrok-free.app/api/odb/film", {
    headers: {
    "ngrok-skip-browser-warning": "skip",
    },
})
    .then(response => response.json())
    .then(data => {
        console.log("FILMS FOUND:")
        console.log(data)
        for (let film of data) {
            createRegistry(film)
        }
    })
    .catch(error => {
        console.error("Error in request:", error);
    });

const addBtn = document.getElementById("btAdd")
const deleteBtns = document.getElementsByClassName("delete")
const editBtns = document.getElementsByClassName("edit")

const overlay = document.getElementById("overlay")

let textNode
const constructedElements = []

addBtn.addEventListener("click", () => {
    overlay.style.display = "flex"
    constructNewRegistryInput()
})

let submitEventListenerFunction

for (const btn of deleteBtns) {
    btn.addEventListener("click", () => {
        overlay.style.display = "flex"

        textNode = document.createTextNode("Você tem certeza que quer deletar esse registro? ")
        overlay.querySelector("h1").appendChild(textNode)

        const li = document.createElement("li")
        li.setAttribute("id", "submit")
        constructedElements.push(li)

        const input = document.createElement("input")
        input.setAttribute("type", "submit")
        input.setAttribute("value", "Sim")

        li.appendChild(input)

        overlay.querySelector("ul").appendChild(li)
        let submitEventListenerFunction = (e) => {
            e.preventDefault()

            const tr = btn.parentElement.parentElement
            const id = tr.querySelector(".id").innerText

            fetch("https://quetzal-novel-man.ngrok-free.app/api/odb/film/" + id, {
                method: "DELETE",
                headers: {
                "ngrok-skip-browser-warning": "skip",
                },
            })
                .then(response => response.json())
                .then(data => {
                    console.log(`FILM WITH ID ${id} DELETED`)
                    console.log(data)
                })
                .catch(error => {
                    console.error("Error in request:", error);
                });

            window.location.reload()
        }

        overlay.querySelector("form").addEventListener("submit", submitEventListenerFunction)
    })
}


for (const btn of editBtns) {
    btn.addEventListener("click", () => {
        overlay.style.display = "flex"

        const tr = btn.parentElement.parentElement

        switch (btn.parentElement.className) {
            case "title":
                constructFieldChange(tr, "title", "título", "text", "80")
                break;
            case "description":
                constructFieldChange(tr, "description", "descrição", "text", "150")
                break;
            case "releaseYear":
                constructFieldChange(tr, "releaseYear", "data de lançamento", "number", "4")
                break;
            case "genre":
                constructFieldChange(tr, "genre", "Gênero", "text")
                break;
            case "duration":
                constructFieldChange(tr, "duration", "duração(min)", "number")
                break;
            case "currentPrice":
                constructFieldChange(tr, "currentPrice", "preço atual", "number", null, null, "0.01")
                break;
        }
    })
}

/* Field Change Construct Functions */

// flag variable to manage the fail message box
let updateFailed = false
// variable to hold the reference of the fail message box to remove it later
let errorBox

const constructFieldChange = (tableRow, field, fieldTitle, fieldType, maxlength, minlength, step) => {
    let fieldWithFirstLetterInUpperCase = field.charAt(0).toUpperCase() + field.slice(1)
    let fieldTitleWithFirstLetterInUpperCase = fieldTitle.charAt(0).toUpperCase() + fieldTitle.slice(1)

    textNode = document.createTextNode("Alterar " + fieldTitleWithFirstLetterInUpperCase)
    overlay.querySelector("h1").appendChild(textNode)
    const ul = overlay.querySelector("ul")

    let li = document.createElement("li")

    const label = document.createElement("label")
    label.setAttribute("for", "inNew" + fieldWithFirstLetterInUpperCase)
    label.innerText = `Novo valor para ${fieldTitle}: `
    li.appendChild(label)

    const br = document.createElement("br")
    li.appendChild(br)

    let input = document.createElement("input")
    input.setAttribute("type", fieldType)
    input.setAttribute("id", "inNew" + fieldWithFirstLetterInUpperCase)
    if (maxlength != null)
        input.setAttribute("maxlength", maxlength)
    if (minlength != null)
        input.setAttribute("minlength", minlength)
    if (step != null)
        input.setAttribute("step", step)
    if (fieldType != "date") {
        let template = "Digite o novo valor para "
        input.setAttribute("placeholder", template + fieldTitle)
    }
    input.setAttribute("required", "");
    li.appendChild(input)

    constructedElements.push(li)
    ul.appendChild(li)

    const liSubmit = document.createElement("li")
    liSubmit.setAttribute("id", "submit")
    constructedElements.push(liSubmit)

    const submitInput = document.createElement("input")
    submitInput.setAttribute("type", "submit")
    submitInput.setAttribute("value", "Alterar")

    liSubmit.appendChild(submitInput)
    ul.appendChild(liSubmit)

    const frm = overlay.querySelector("form")

    submitEventListenerFunction = (e) => {
        // preventing the default behavior from "submit" event
        e.preventDefault()

        // Checking if there's already a fail message box in the screen
        if (updateFailed) {
            // removing it
            errorBox.remove()
            // turning the flag variable back to false
            updateFailed = false
        }

        fetch("https://quetzal-novel-man.ngrok-free.app/api/odb/film", {
            method: "PUT",
            headers: {
            "ngrok-skip-browser-warning": "skip",
            "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "id": tr.querySelector(".id").innerText,
                "title": tr.querySelector(".title").innerText,
                "description": tr.querySelector(".description").innerText,
                "releaseYear": parseInt(tr.querySelector(".releaseYear").innerText),
                "genre": tr.querySelector(".genre").innerText,
                "duration": parseInt(tr.querySelector(".duration").innerText),
                "currentPrice": parseFloat(tr.querySelector(".currentPrice").innerText)
            })
        })
            .then(response => response.json())
            .then(data => {
                console.log("FILM ENTITY UPDATED:")
                console.log(data)
            })
            .catch(error => {
                console.error("Error in request:", error);
            });

        window.location.reload()
    }

    frm.addEventListener("submit", submitEventListenerFunction)
}


/* Descontruct OverLay */

// Adding a event listener to event "click" in the overlay close button
overlay.querySelector("button#btClose").addEventListener("click", () => {
    overlay.querySelector("h1").removeChild(textNode)
    // removing all constructed elements stored in the array constructedElements
    for (const element of constructedElements)
        element.remove()

    // Checking if there's still a fail message box in the screen
    if (updateFailed) {
        // removing it
        errorBox.remove()
        // turning the flag variable back to false
        updateFailed = false
    }

    // removing event listener to event "submit" from form element
    overlay.querySelector("form").removeEventListener("submit", submitEventListenerFunction)
})

function constructNewRegistryInput() {
    const ul = overlay.querySelector("ul")

    textNode = document.createTextNode("Adicionar Novo Registro")
    overlay.querySelector("h1").appendChild(textNode)

    const liTitle = document.createElement("li")
    const labelTitle = document.createElement("label")
    labelTitle.setAttribute("for", "inTitle")
    const textTitle = document.createTextNode("Título:")
    labelTitle.appendChild(textTitle)
    liTitle.appendChild(labelTitle)
    const brTitle = document.createElement("br")
    liTitle.appendChild(brTitle)
    const inputTitle = document.createElement("input")
    inputTitle.setAttribute("type", "text")
    inputTitle.setAttribute("id", "inTitle")
    inputTitle.setAttribute("maxlength", "80")
    inputTitle.setAttribute("placeholder", "Digite o título")
    inputTitle.setAttribute("required", "")
    liTitle.appendChild(inputTitle)
    constructedElements.push(liTitle)
    ul.appendChild(liTitle)

    const liDescription = document.createElement("li")
    const labelDescription = document.createElement("label")
    labelDescription.setAttribute("for", "inDescription")
    const textDescription = document.createTextNode("Descrição:")
    labelDescription.appendChild(textDescription)
    liDescription.appendChild(labelDescription)
    const brDescription = document.createElement("br")
    liDescription.appendChild(brDescription)
    const inputDescription = document.createElement("input")
    inputDescription.setAttribute("type", "text")
    inputDescription.setAttribute("id", "inDescription")
    inputDescription.setAttribute("maxlength", "150")
    inputDescription.setAttribute("placeholder", "Digite a descrição")
    inputDescription.setAttribute("required", "")
    liDescription.appendChild(inputDescription)
    constructedElements.push(liDescription)
    ul.appendChild(liDescription)

    const liReleaseYear = document.createElement("li")
    const labelReleaseYear = document.createElement("label")
    labelReleaseYear.setAttribute("for", "inReleaseYear")
    const textReleaseYear = document.createTextNode("Ano de lançamento:")
    labelReleaseYear.appendChild(textReleaseYear)
    liReleaseYear.appendChild(labelReleaseYear)
    const brReleaseYear = document.createElement("br")
    liReleaseYear.appendChild(brReleaseYear)
    const inputReleaseYear = document.createElement("input")
    inputReleaseYear.setAttribute("type", "number")
    inputReleaseYear.setAttribute("id", "inReleaseYear")
    inputReleaseYear.setAttribute("maxlength", "4")
    inputReleaseYear.setAttribute("placeholder", "Digite o ano de lançamento")
    inputReleaseYear.setAttribute("required", "")
    liReleaseYear.appendChild(inputReleaseYear)
    constructedElements.push(liReleaseYear)
    ul.appendChild(liReleaseYear)

    const liGenre = document.createElement("li")
    const labelGenre = document.createElement("label")
    labelGenre.setAttribute("for", "inGenre")
    const textGenre = document.createTextNode("Gênero:")
    labelGenre.appendChild(textGenre)
    liGenre.appendChild(labelGenre)
    const brGenre = document.createElement("br")
    liGenre.appendChild(brGenre)
    const inputGenre = document.createElement("input")
    inputGenre.setAttribute("type", "text")
    inputGenre.setAttribute("id", "inGenre")
    inputGenre.setAttribute("placeholder", "Digite o gênero")
    inputGenre.setAttribute("required", "")
    liGenre.appendChild(inputGenre)
    constructedElements.push(liGenre)
    ul.appendChild(liGenre)

    const liDuration = document.createElement("li")
    const labelDuration = document.createElement("label")
    labelDuration.setAttribute("for", "inDuration")
    const textDuration = document.createTextNode("Duração(min):")
    labelDuration.appendChild(textDuration)
    liDuration.appendChild(labelDuration)
    const brDuration = document.createElement("br")
    liDuration.appendChild(brDuration)
    const inputDuration = document.createElement("input")
    inputDuration.setAttribute("type", "number")
    inputDuration.setAttribute("id", "inDuration")
    inputDuration.setAttribute("placeholder", "Digite a duração do filme(min)")
    inputDuration.setAttribute("required", "")
    liDuration.appendChild(inputDuration)
    constructedElements.push(liDuration)
    ul.appendChild(liDuration)

    const liCurrentPrice = document.createElement("li")
    const labelCurrentPrice = document.createElement("label")
    labelCurrentPrice.setAttribute("for", "inCurrentPrice")
    const textCurrentPrice = document.createTextNode("Preço atual:")
    labelCurrentPrice.appendChild(textCurrentPrice)
    liCurrentPrice.appendChild(labelCurrentPrice)
    const brCurrentPrice = document.createElement("br")
    liCurrentPrice.appendChild(brCurrentPrice)
    const inputCurrentPrice = document.createElement("input")
    inputCurrentPrice.setAttribute("type", "number")
    inputCurrentPrice.setAttribute("step", "0.01")
    inputCurrentPrice.setAttribute("id", "inCurrentPrice")
    inputCurrentPrice.setAttribute("placeholder", "Digite o preço atual")
    inputCurrentPrice.setAttribute("required", "")
    liCurrentPrice.appendChild(inputCurrentPrice)
    constructedElements.push(liCurrentPrice);
    ul.appendChild(liCurrentPrice)

    const liSubmit = document.createElement("li")
    liSubmit.setAttribute("id", "submit")
    const inputSubmit = document.createElement("input")
    inputSubmit.setAttribute("type", "submit")
    inputSubmit.setAttribute("value", "Adicionar")
    liSubmit.appendChild(inputSubmit)
    constructedElements.push(liSubmit)
    ul.appendChild(liSubmit)

    const inputs = overlay.getElementsByTagName("input")

    submitEventListenerFunction = (e) => {
        // preventing the default behavior from "submit" event
        e.preventDefault()

        // Checking if there's already a fail message box in the screen
        if (updateFailed) {
            // removing it
            errorBox.remove()
            // turning the flag variable back to false
            updateFailed = false
        }

        fetch("https://quetzal-novel-man.ngrok-free.app/api/odb/film", {
            method: "POST",
            headers: {
            "ngrok-skip-browser-warning": "skip",
            "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "title": inputs[0],
                "description": inputs[1],
                "releaseYear": parseInt(inputs[2]),
                "genre": inputs[3],
                "duration": parseInt(inputs[4]),
                "currentPrice": parseFloat(inputs[5])
            })
        })
            .then(response => response.json())
            .then(data => {
                console.log("FILM ENTITY CREATED:")
                console.log(data)
            })
            .catch(error => {
                console.error("Error in request:", error);
            });
        
        window.location.reload()
    }

    overlay.querySelector("form").addEventListener("submit", submitEventListenerFunction)
}

function createRegistry(film) {
    const tableRow = document.createElement("tr")

    const editButton = document.createElement("button")
    editButton.setAttribute("class", "edit")
    const editIcon = document.createElement("i")
    editIcon.setAttribute("class", "fa-regular fa-pen-to-square")

    const tableData1 = document.createElement("td")
    tableData1.setAttribute("class", "id")
    const idData = document.createTextNode(film.id)
    tableData1.appendChild(idData)
    tableRow.appendChild(tableData1)

    const edBt2 = editButton.cloneNode()
    edBt2.appendChild(editIcon.cloneNode())

    const tableData2 = document.createElement("td")
    tableData2.setAttribute("class", "title")
    const titleData = document.createTextNode(film.title)
    tableData2.appendChild(titleData)
    tableData2.appendChild(edBt2)
    tableRow.appendChild(tableData2)

    const edBt3 = editButton.cloneNode()
    edBt3.appendChild(editIcon.cloneNode())

    const tableData3 = document.createElement("td")
    tableData3.setAttribute("class", "description")
    const descriptionData = document.createTextNode(film.description)
    tableData3.appendChild(descriptionData)
    tableData3.appendChild(edBt3)
    tableRow.appendChild(tableData3)

    const edBt4 = editButton.cloneNode()
    edBt4.appendChild(editIcon.cloneNode())

    const tableData4 = document.createElement("td")
    tableData4.setAttribute("class", "releaseYear")
    const releaseYearData = document.createTextNode(film.releaseYear)
    tableData4.appendChild(releaseYearData)
    tableData4.appendChild(edBt4)
    tableRow.appendChild(tableData4)

    const edBt5 = editButton.cloneNode()
    edBt5.appendChild(editIcon.cloneNode())

    const tableData5 = document.createElement("td")
    tableData5.setAttribute("class", "genre")
    const genreData = document.createTextNode(film.genre)
    tableData5.appendChild(genreData)
    tableData5.appendChild(edBt5)
    tableRow.appendChild(tableData5)

    const edBt6 = editButton.cloneNode()
    edBt6.appendChild(editIcon.cloneNode())

    const tableData6 = document.createElement("td")
    tableData6.setAttribute("class", "duration")
    const durationData = document.createTextNode(film.duration)
    tableData6.appendChild(durationData)
    tableData6.appendChild(edBt6)
    tableRow.appendChild(tableData6)

    const edBt7 = editButton.cloneNode()
    edBt7.appendChild(editIcon.cloneNode())

    const tableData7 = document.createElement("td")
    tableData7.setAttribute("class", "currentPrice")
    const currentPriceData = document.createTextNode(film.currentPrice)
    tableData7.appendChild(currentPriceData)
    tableData7.appendChild(edBt7)
    tableRow.appendChild(tableData7)

    const tableData8 = document.createElement("td")
    tableData8.setAttribute("class", "tdDel")
    const deleteButton = document.createElement("button")
    deleteButton.setAttribute("class", "delete")
    const deleteIcon = document.createElement("i")
    deleteIcon.setAttribute("class", "fa-solid fa-trash")
    deleteButton.appendChild(deleteIcon)
    tableData8.appendChild(deleteButton)
    tableRow.appendChild(tableData8)
    
    table.appendChild(tableRow)
}

/* Support Functions - Basically copied from register.js */

// function to create a fail message box in user screen with the text passed by parameter
const failUpdate = (errMsg) => {
    // creating a li html element
    const listItem = document.createElement("li")
    // creating a div html element
    const messageErrorBox = document.createElement("div")
    // creating a text node having error message passed by parameter
    const errorMessage = document.createTextNode(errMsg)

    // putting the error message inside the div html element
    messageErrorBox.appendChild(errorMessage)
    // stylying the div html element
    messageErrorBox.style.backgroundColor = "rgba(255, 0, 0, 0.2)"
    messageErrorBox.style.color = "red"
    messageErrorBox.style.border = "1px solid red"
    messageErrorBox.style.padding = "2px 5px"

    // putting the div html element inside the li html element
    listItem.appendChild(messageErrorBox)

    // puting li html element inside the ul html element
    overlay.querySelector("ul").appendChild(listItem)

    // Updating flag variable to true
    updateFailed = true
    // storing the reference to the fail message box to remove it later
    errorBox = listItem
}