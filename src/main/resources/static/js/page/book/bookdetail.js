$(document).ready(function () {
    setStars();
    setIndex();
    setButtonEventListener();
    setChangeEventListener();
});

function setButtonEventListener() {
    document.getElementById('button-addon1').addEventListener('click', function () {
        const quantityContainer = document.getElementById("bookQuantity")
        const quantity = Number(document.getElementById("bookQuantity").value);

        if (quantity <= 0) {
            quantityContainer.value = 0;
        } else {
            quantityContainer.value = quantity - 1;
        }
    });

    document.getElementById('button-addon2').addEventListener('click', function () {
        const quantityContainer = document.getElementById("bookQuantity")
        const quantity = Number(document.getElementById("bookQuantity").value);
        const stock = Number(document.getElementById("bookStock").textContent.replace("권 남음", ""));

        if (quantity < stock) {
            quantityContainer.value = quantity + 1;
        } else {
            quantityContainer.value = stock;
        }
    });
}

function setChangeEventListener() {
    $("#bookQuantity").on("propertychange change keyup paste input", function () {
        const quantityContainer = document.getElementById("bookQuantity")
        const quantity = Number(document.getElementById("bookQuantity").value);

        let stockText = document.getElementById("bookStock").textContent;
        const stock = Number(stockText.replace("권 남음", ""));

        if (quantity >= stock) {
            quantityContainer.value = stock;
        } else if (quantity < 0) {
            quantityContainer.value = 0;
        }
    });
}

function setStars() {
    const score = Number(document.getElementById("starScoreContainer").textContent);

    if (score === 0) {
        const newItag = document.createElement('i');
        newItag.classList.add("fa", "fa-star-half-alt");
        document.getElementById("starContainer").appendChild(newItag);
    }

    for (let i = 0; i < score; i++) {
        const newItag = document.createElement('i');
        newItag.classList.add("fa", "fa-star");
        document.getElementById("starContainer").appendChild(newItag);
    }

    document.getElementById("starContainer").appendChild(document.getElementById("scoreText"));
    document.getElementById("starScoreContainer").remove();
}

function setIndex() {
    let indexString = document.getElementById("indexPtag").textContent;

    if (indexString.includes("<toc>")) {
        indexString = removeHtmlTags(indexString);
        const indexArray = indexString.split("/");

        for (let step in indexArray) {
            const newPtag = document.createElement('p');
            newPtag.textContent = indexArray[step];

            document.getElementById("indexPtag").parentNode.appendChild(newPtag);
        }
    }

    document.getElementById("indexPtag").remove();
}

function removeHtmlTags(string) {
    string = string.replace("<toc>", "");
    string = string.replace("</toc>", "");
    string = string.replace("&lt;p&gt;", "");
    string = string.replace("&lt;/p&gt;", "");
    string = string.replaceAll("&lt;br /&gt;", "/");
    string = string.replaceAll("&lt;br/&gt;", "/");
    string = string.replaceAll("&lt;BR&gt;", "/");

    return string;
}
