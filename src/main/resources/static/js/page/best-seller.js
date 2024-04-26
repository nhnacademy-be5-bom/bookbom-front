document.addEventListener('DOMContentLoaded', function () {
    // 10개씩 보기가 기본값
    document.querySelector('.page-size option[value="10"]').selected = true;

    // 수량 감소 버튼 클릭
    document.querySelectorAll('.quantity-decrease').forEach(button => {
        button.addEventListener('click', function () {
            let quantityInput = button.parentElement.querySelector('.quantity-input');
            let currentQuantity = parseInt(quantityInput.value);
            if (isNaN(currentQuantity)) {
                currentQuantity = 1; // currentValue가 비어있거나 NaN인 경우 1로 설정
            } else if (currentQuantity > 1) {
                currentQuantity -= 1;
            }
            quantityInput.value = currentQuantity;
        });
    });

    // 수량 증가 버튼 클릭
    document.querySelectorAll('.quantity-increase').forEach(button => {
        button.addEventListener('click', function () {
            let quantityInput = button.parentElement.querySelector('.quantity-input');
            let currentQuantity = parseInt(quantityInput.value);
            if (isNaN(currentQuantity)) {
                currentQuantity = 1;
            } else {
                currentQuantity += 1;
                if (currentQuantity > 99) {
                    currentQuantity = 99;
                }
            }
            quantityInput.value = currentQuantity;
        });
    });

    // 수량 입력 필드의 값 변경
    document.querySelectorAll('.quantity-input').forEach(input => {
        // 입력 값이 바뀔 때
        input.addEventListener('input', function () {
            let currentValue = parseInt(this.value, 10);
            if (!isNaN(currentValue)) {
                currentValue = Math.max(1, currentValue);
                currentValue = Math.min(99, currentValue); // 최대값 99로 제한
                this.value = currentValue;
            }
        });
        // 입력 값 포커스가 해제될 때
        input.addEventListener('blur', function () {
            let currentValue = parseInt(this.value, 10);
            if (isNaN(currentValue)) {
                currentValue = 1; // currentValue가 비어있거나 NaN인 경우 0으로 설정
            }
            this.value = currentValue;
        });
    });


    // 페이지 크기 선택
    let pageSizeParam = new URLSearchParams(window.location.search).get('size');
    let pageSizeSelect = document.querySelector('.page-size');
    if (!pageSizeParam) {
        pageSizeSelect.value = "10";
    } else {
        pageSizeSelect.value = pageSizeParam;
    }
    pageSizeSelect.addEventListener('change', function () {
        let selectedPageSize = this.value;
        let currentUrl = new URL(window.location.href);
        currentUrl.searchParams.set("page", 0);
        currentUrl.searchParams.set('size', selectedPageSize);
        window.location.replace(currentUrl.toString());
    });

    // 장바구니 버튼 클릭
    document.querySelectorAll('.cart-btn').forEach(button => {
        button.addEventListener('click', function () {
            let requestData = [];
            // 수량 입력 필드를 찾아서 값을 가져옴
            const quantityInput = this.closest('.quantity-and-buttons').querySelector('.quantity-input');
            const quantity = parseInt(quantityInput.value);

            // 책 정보를 가져옴
            const bookInfo = this.closest('.book-info');
            const cardBody = bookInfo.querySelector('.card-body')
            const thumbnail = bookInfo.querySelector('.book-thumbnail').src;
            const title = cardBody.querySelector('.book-title').textContent;
            const price = parseInt(cardBody.querySelector('.book-cost').textContent.replace('원', ''));
            const discountPrice = parseInt(cardBody.querySelector('.book-discount-cost').textContent.replace('원', ''));
            const bookId = bookInfo.getAttribute('data-book-id');

            // 요청 데이터 생성
            requestData.push({
                bookId: parseInt(bookId),
                thumbnail: thumbnail,
                title: title,
                price: price,
                discountPrice: discountPrice,
                quantity: quantity
            });
            addToCart(requestData);
            console.log(requestData)
        });
    });

    // 주문 폼 생성
    document.querySelector('.order-btn').addEventListener('click', function () {

        const bookInfo = this.closest('.book-info');
        const bookId = bookInfo.getAttribute('data-book-id');
        const quantityInput = this.closest('.quantity-and-buttons').querySelector('.quantity-input');
        const quantity = parseInt(quantityInput.value);

        const form = document.createElement('form');
        form.method = 'post';
        form.action = '/order/wrapper';

        const bookIdInput = document.createElement('input');
        bookIdInput.type = 'hidden';
        bookIdInput.name = `beforeOrderRequests[${index}].bookId`;
        bookIdInput.value = bookId;
        form.appendChild(bookIdInput);


        const quantityInputHidden = document.createElement('input');
        quantityInputHidden.type = 'hidden';
        quantityInputHidden.name = `beforeOrderRequests[${index}].quantity`;
        quantityInputHidden.value = quantity;
        form.appendChild(quantityInputHidden);
        document.body.appendChild(form);
        form.submit();
    });

    // 장바구니에 담기 요청
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
