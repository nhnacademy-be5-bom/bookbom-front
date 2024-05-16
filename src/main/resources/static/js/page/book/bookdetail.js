$(document).ready(function () {
    setStars();
    setIndex();
    setButtonEventListener();
    setChangeEventListener();

    document.querySelectorAll('.order-one').forEach(button => {
        button.addEventListener('click', function () {

            const form = document.createElement('form');
            form.method = 'post';
            form.action = '/order/wrapper';

            const quantityInput = document.querySelector('.quantity-and-buttons').querySelector('.quantity-input');
            const quantity = parseInt(quantityInput.value);
            const bookId = document.querySelector('.quantity-and-buttons').querySelector('.book-id-input').value;

            const bookIdInput = document.createElement('input');
            bookIdInput.type = 'hidden';
            bookIdInput.name = `beforeOrderRequests[0].bookId`;
            bookIdInput.value = bookId;
            form.appendChild(bookIdInput);

            const quantityInputHidden = document.createElement('input');
            quantityInputHidden.type = 'hidden';
            quantityInputHidden.name = `beforeOrderRequests[0].quantity`;
            quantityInputHidden.value = quantity;
            form.appendChild(quantityInputHidden);

            document.body.appendChild(form);
            form.submit();
        });
    });

// 장바구니 버튼 클릭
    document.querySelectorAll('.cart-btn').forEach(button => {
        button.addEventListener('click', function () {
            let requestData = [];
            const quantityInput = document.querySelector('.quantity-and-buttons').querySelector('.quantity-input');
            const quantity = parseInt(quantityInput.value);
            const bookId = document.querySelector('.quantity-and-buttons').querySelector('.book-id-input').value;
            const thumbnail = document.querySelector('.thumbnail-container').querySelector('.book-thumbnail').src;
            const title = document.querySelector('.book-detail-container').querySelector('.book-title').textContent;
            const price = parseInt(document.querySelector('.book-detail-container').querySelector('.book-cost')
                .textContent.replace('원', ''));
            const discountPrice = parseInt(document.querySelector('.book-detail-container').querySelector('.book-discount-cost')
                .textContent.replace('/', '').replace('원', ''));

            requestData.push({
                bookId: parseInt(bookId),
                thumbnail: thumbnail,
                title: title,
                price: price,
                discountPrice: discountPrice,
                quantity: quantity
            });

            addToCart(requestData);
        });

        function addToCart(requestData) {
            // AJAX를 사용하여 POST 요청 전송
            fetch('/cart', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(requestData),
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('네트워크 에러가 발생했습니다.');
                    }
                    return response.json();
                })
                .then(data => {
                    let myModal = new bootstrap.Modal(document.getElementById('successModal'), {
                        keyboard: false
                    });
                    myModal.show();
                    document.getElementById('goToCart').addEventListener('click', () => {
                        window.location.href = '/cart';
                    });
                })
                .catch(error => {
                    console.error('Error:', error);
                });
        }
    });
});

function setButtonEventListener() {
    document.getElementById('button-addon1').addEventListener('click', function () {
        const quantityContainer = document.getElementById("bookQuantity")
        const quantity = Number(document.getElementById("bookQuantity").value);

        if (quantity <= 1) {
            quantityContainer.value = 1;
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
    if (document.getElementById("indexPtag") !== null) {
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
}

function removeHtmlTags(string) {
    string = string.replace("<toc>", "")
        .replace("</toc>", "")
        .replace("&lt;p&gt;", "")
        .replace("&lt;/p&gt;", "")
        .replaceAll("&lt;br /&gt;", "/")
        .replaceAll("&lt;br/&gt;", "/")
        .replaceAll("&lt;BR&gt;", "/")
        .replaceAll("&lt;B&gt;", "/")
        .replaceAll("&lt;/B&gt;", "/")

    return string;
}
