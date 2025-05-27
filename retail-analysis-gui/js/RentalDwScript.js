async function loadRentals() {
    const table = document.querySelector("table")

    try {
        const response = await fetch("https://quetzal-novel-man.ngrok-free.app/api/odb/film", {
            headers: {
                "ngrok-skip-browser-warning": "skip",
            },
        });

        const data = await response.json();

        console.log("RENTALS FOUND:");
        console.log(data);

        for (let rental of data) {
            createRegistry(rental);
        }

    } catch (error) {
        console.error("Error in request:", error);
    }

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
            let submitEventListenerFunction = async (e) => {
                e.preventDefault()

                const tr = btn.parentElement.parentElement
                const id = tr.querySelector(".id").innerText

                await fetch("https://quetzal-novel-man.ngrok-free.app/api/odb/film/" + id, {
                    method: "DELETE",
                    headers: {
                    "ngrok-skip-browser-warning": "skip",
                    },
                })
                    .then(response => response.json())
                    .then(data => {
                        console.log(`RENTAL WITH ID ${id} DELETED`)
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
                case "timeId":
                    constructFieldChange(tr, "timeId", "id do tempo", "number")
                    break;
                case "filmId":
                    constructFieldChange(tr, "filmId", "id do filme", "number")
                    break;
                case "clientId":
                    constructFieldChange(tr, "clientId", "id do cliente", "number")
                    break;
                case "storeId":
                    constructFieldChange(tr, "storeId", "id da loja", "number")
                    break;
                case "paidValue":
                    constructFieldChange(tr, "paidValue", "valor pago", "number", null, null, "0.01")
                    break;
                case "quantity":
                    constructFieldChange(tr, "quantity", "quantidade", "number")
                    break;
            }
        })
    }

    const constructFieldChange = async (tr, field, fieldTitle, fieldType, maxlength, minlength, step) => {
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
            let template = "Digite valor para "
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

        submitEventListenerFunction = async (e) => {
            // preventing the default behavior from "submit" event
            e.preventDefault()

            const film = {}
            film.id = tr.querySelector(".id").innerText
            film.title = field == "title" ? input.value : tr.querySelector(".title").innerText
            film.description = field == "description" ? input.value : tr.querySelector(".description").innerText
            film.releaseYear = field == "releaseYear" ? parseInt(input.value) : parseInt(tr.querySelector(".releaseYear").innerText)
            film.genre = field == "genre" ? input.value : tr.querySelector(".genre").innerText
            film.duration = field == "duration" ? parseInt(input.value) : parseInt(tr.querySelector(".duration").innerText)
            film.currentPrice = field == "currentPrice" ? parseFloat(input.value) : parseFloat(tr.querySelector(".currentPrice").innerText)

            await fetch("https://quetzal-novel-man.ngrok-free.app/api/odb/film", {
                method: "PUT",
                headers: {
                "ngrok-skip-browser-warning": "skip",
                "Content-Type": "application/json"
                },
                body: JSON.stringify(film)
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

        // removing event listener to event "submit" from form element
        overlay.querySelector("form").removeEventListener("submit", submitEventListenerFunction)
    })

    function constructNewRegistryInput() {
        const ul = overlay.querySelector("ul")

        textNode = document.createTextNode("Adicionar Novo Registro")
        overlay.querySelector("h1").appendChild(textNode)

        const liTimeId = document.createElement("li")
        const labelTimeId = document.createElement("label")
        labelTimeId.setAttribute("for", "inTimeId")
        const textTimeId = document.createTextNode("Id do registro do tempo:")
        labelTimeId.appendChild(textTimeId)
        liTimeId.appendChild(labelTimeId)
        const brTimeId = document.createElement("br")
        liTimeId.appendChild(brTimeId)
        const inputTimeId = document.createElement("input")
        inputTimeId.setAttribute("type", "number")
        inputTimeId.setAttribute("id", "inTimeId")
        inputTimeId.setAttribute("placeholder", "Digite o id do tempo")
        inputTimeId.setAttribute("required", "")
        liTimeId.appendChild(inputTimeId)
        constructedElements.push(liTimeId)
        ul.appendChild(liTimeId)

        const liFilmId = document.createElement("li")
        const labelFilmId = document.createElement("label")
        labelFilmId.setAttribute("for", "inFilmId")
        const textFilmId = document.createTextNode("Id do registro do filme:")
        labelFilmId.appendChild(textFilmId)
        liFilmId.appendChild(labelFilmId)
        const brFilmId = document.createElement("br")
        liFilmId.appendChild(brFilmId)
        const inputFilmId = document.createElement("input")
        inputFilmId.setAttribute("type", "number")
        inputFilmId.setAttribute("id", "inFilmId")
        inputFilmId.setAttribute("placeholder", "Digite o id do filme")
        inputFilmId.setAttribute("required", "")
        liFilmId.appendChild(inputFilmId)
        constructedElements.push(liFilmId)
        ul.appendChild(liFilmId)

        const liClientId = document.createElement("li")
        const labelClientId = document.createElement("label")
        labelClientId.setAttribute("for", "inClientId")
        const textClientId = document.createTextNode("Id do registro do cliente:")
        labelClientId.appendChild(textClientId)
        liClientId.appendChild(labelClientId)
        const brClientId = document.createElement("br")
        liClientId.appendChild(brClientId)
        const inputClientId = document.createElement("input")
        inputClientId.setAttribute("type", "number")
        inputClientId.setAttribute("id", "inClientId")
        inputClientId.setAttribute("placeholder", "Digite o id do cliente")
        inputClientId.setAttribute("required", "")
        liClientId.appendChild(inputClientId)
        constructedElements.push(liClientId)
        ul.appendChild(liClientId)

        const liStoreId = document.createElement("li")
        const labelStoreId = document.createElement("label")
        labelStoreId.setAttribute("for", "inStoreId")
        const textStoreId = document.createTextNode("Id do registro da loja:")
        labelStoreId.appendChild(textStoreId)
        liStoreId.appendChild(labelStoreId)
        const brStoreId = document.createElement("br")
        liStoreId.appendChild(brStoreId)
        const inputStoreId = document.createElement("input")
        inputStoreId.setAttribute("type", "number")
        inputStoreId.setAttribute("id", "inStoreId")
        inputStoreId.setAttribute("placeholder", "Digite o id da loja")
        inputStoreId.setAttribute("required", "")
        liStoreId.appendChild(inputStoreId)
        constructedElements.push(liStoreId)
        ul.appendChild(liStoreId)

        const liPaidValue = document.createElement("li")
        const labelPaidValue = document.createElement("label")
        labelPaidValue.setAttribute("for", "inPaidValue")
        const textPaidValue = document.createTextNode("Valor pago:")
        labelPaidValue.appendChild(textPaidValue)
        liPaidValue.appendChild(labelPaidValue)
        const brPaidValue = document.createElement("br")
        liPaidValue.appendChild(brPaidValue)
        const inputPaidValue = document.createElement("input")
        inputPaidValue.setAttribute("type", "number")
        inputPaidValue.setAttribute("step", "0.01")
        inputPaidValue.setAttribute("id", "inPaidValue")
        inputPaidValue.setAttribute("placeholder", "Digite o valor pago")
        inputPaidValue.setAttribute("required", "")
        liPaidValue.appendChild(inputPaidValue)
        constructedElements.push(liPaidValue)
        ul.appendChild(liPaidValue)

        const liQuantity = document.createElement("li")
        const labelQuantity = document.createElement("label")
        labelQuantity.setAttribute("for", "inQuantity")
        const textQuantity = document.createTextNode("Quantidade:")
        labelQuantity.appendChild(textQuantity)
        liQuantity.appendChild(labelQuantity)
        const brQuantity = document.createElement("br")
        liQuantity.appendChild(brQuantity)
        const inputQuantity = document.createElement("input")
        inputQuantity.setAttribute("type", "number")
        inputQuantity.setAttribute("id", "inQuantity")
        inputQuantity.setAttribute("placeholder", "Digite a quantidade")
        inputQuantity.setAttribute("required", "")
        liQuantity.appendChild(inputQuantity)
        constructedElements.push(liQuantity);
        ul.appendChild(liQuantity)

        const liSubmit = document.createElement("li")
        liSubmit.setAttribute("id", "submit")
        const inputSubmit = document.createElement("input")
        inputSubmit.setAttribute("type", "submit")
        inputSubmit.setAttribute("value", "Adicionar")
        liSubmit.appendChild(inputSubmit)
        constructedElements.push(liSubmit)
        ul.appendChild(liSubmit)

        const inputs = overlay.getElementsByTagName("input")

        submitEventListenerFunction = async (e) => {
            // preventing the default behavior from "submit" event
            e.preventDefault()

            await fetch("https://quetzal-novel-man.ngrok-free.app/api/odb/film", {
                method: "POST",
                headers: {
                "ngrok-skip-browser-warning": "skip",
                "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    "timeId": parseInt(inputs[0].value),
                    "filmId": parseInt(inputs[1].value),
                    "clientId": parseInt(inputs[2].value),
                    "storeId": parseInt(inputs[3].value),
                    "paidValue": parseFloat(inputs[4].value),
                    "quantity": parseInt(inputs[5].value)
                })
            })
                .then(response => response.json())
                .then(data => {
                    console.log("RENTAL ENTITY CREATED:")
                    console.log(data)
                })
                .catch(error => {
                    console.error("Error in request:", error);
                });
            
            window.location.reload()
        }

        overlay.querySelector("form").addEventListener("submit", submitEventListenerFunction)
    }

    function createRegistry(rental) {
        const tableRow = document.createElement("tr")

        const editButton = document.createElement("button")
        editButton.setAttribute("class", "edit")
        const editIcon = document.createElement("i")
        editIcon.setAttribute("class", "fa-regular fa-pen-to-square")

        const tableData1 = document.createElement("td")
        tableData1.setAttribute("class", "id")
        const idData = document.createTextNode(rental.id)
        tableData1.appendChild(idData)
        tableRow.appendChild(tableData1)

        const edBt2 = editButton.cloneNode()
        edBt2.appendChild(editIcon.cloneNode())

        const tableData2 = document.createElement("td")
        tableData2.setAttribute("class", "timeId")
        const timeIdData = document.createTextNode(rental.timeId)
        tableData2.appendChild(timeIdData)
        tableData2.appendChild(edBt2)
        tableRow.appendChild(tableData2)

        const edBt3 = editButton.cloneNode()
        edBt3.appendChild(editIcon.cloneNode())

        const tableData3 = document.createElement("td")
        tableData3.setAttribute("class", "filmId")
        const filmIdData = document.createTextNode(film.filmId)
        tableData3.appendChild(filmIdData)
        tableData3.appendChild(edBt3)
        tableRow.appendChild(tableData3)

        const edBt4 = editButton.cloneNode()
        edBt4.appendChild(editIcon.cloneNode())

        const tableData4 = document.createElement("td")
        tableData4.setAttribute("class", "clientId")
        const clientIdData = document.createTextNode(film.clientId)
        tableData4.appendChild(clientIdData)
        tableData4.appendChild(edBt4)
        tableRow.appendChild(tableData4)

        const edBt5 = editButton.cloneNode()
        edBt5.appendChild(editIcon.cloneNode())

        const tableData5 = document.createElement("td")
        tableData5.setAttribute("class", "storeId")
        const storeIdData = document.createTextNode(film.storeId)
        tableData5.appendChild(storeIdData)
        tableData5.appendChild(edBt5)
        tableRow.appendChild(tableData5)

        const edBt6 = editButton.cloneNode()
        edBt6.appendChild(editIcon.cloneNode())

        const tableData6 = document.createElement("td")
        tableData6.setAttribute("class", "paidValue")
        const paidValueData = document.createTextNode(film.paidValue)
        tableData6.appendChild(paidValueData)
        tableData6.appendChild(edBt6)
        tableRow.appendChild(tableData6)

        const edBt7 = editButton.cloneNode()
        edBt7.appendChild(editIcon.cloneNode())

        const tableData7 = document.createElement("td")
        tableData7.setAttribute("class", "quantity")
        const quantityData = document.createTextNode(film.quantity)
        tableData7.appendChild(quantityData)
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

}

loadRentals();