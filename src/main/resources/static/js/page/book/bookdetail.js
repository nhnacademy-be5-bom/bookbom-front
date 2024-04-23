$(document).ready(function () {
    setStars();
    setIndex();
});

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
        indexString = indexString.replace("<toc>", "");
        indexString = indexString.replace("</toc>", "");
        const indexArray = indexString.split("&lt;br /&gt;");

        for (let step in indexArray) {
            const newPtag = document.createElement('p');
            newPtag.textContent = indexArray[step];

            document.getElementById("indexPtag").parentNode.appendChild(newPtag);
        }
    }

    document.getElementById("indexPtag").remove();
}
