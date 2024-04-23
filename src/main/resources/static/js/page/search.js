document.addEventListener('DOMContentLoaded', function () {
    let queryParams = new URLSearchParams(window.location.search);
    let searchCondition = queryParams.get('search');
    let checkboxes = document.querySelectorAll('.search-check');
    checkboxes.forEach(function (checkbox) {
        if (searchCondition === checkbox.id) {
            checkbox.checked = true;
        }
        // 체크박스 체크했을 때
        checkbox.addEventListener('change', function (event) {
            const keyword = queryParams.get('keyword');
            let newUrl = window.location.pathname + '?keyword=' + keyword;
            // 체크했다면 검색 조건 붙여서 요청
            if (event.target.checked) {
                // 다른 박스 체크 해제
                let searchCondition = event.target.id;
                newUrl += '&search=' + searchCondition;
                checkboxes.forEach(function (box) {
                    if (box !== event.target) {
                        box.checked = false;
                    }
                });
            }

            window.location.href = newUrl;
        });
    });

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

    // 전체 선택
    let selectAllButton = document.querySelector('.select-all');
    let bookCheckboxes = document.querySelectorAll('.book-checkbox');

    selectAllButton.addEventListener('click', function () {
        let allChecked = true;
        bookCheckboxes.forEach(function (checkbox) {
            if (!checkbox.checked) {
                allChecked = false;
            }
        });

        bookCheckboxes.forEach(function (checkbox) {
            checkbox.checked = !allChecked;
        });
    });

    // 검색 결과 정렬
    document.querySelectorAll('.sort-condition').forEach(condition => {
        condition.addEventListener('click', function () {
            let sortCondition = this.id.trim();
            let currentUrl = new URL(window.location.href);
            currentUrl.searchParams.delete("page");
            currentUrl.searchParams.set('sorted', sortCondition);
            window.location.href = currentUrl.toString();
        });
    });

    // 페이지 크기 선택
    let pageSizeParam = new URLSearchParams(window.location.search).get('size');
    let pageSizeSelect = document.querySelector('.page-size');
    if (!pageSizeParam) {
        pageSizeSelect.value = "5";
    } else {
        pageSizeSelect.value = pageSizeParam;
    }
    pageSizeSelect.addEventListener('change', function () {
        let selectedPageSize = this.value;
        let currentUrl = new URL(window.location.href);
        currentUrl.searchParams.delete("sorted");
        currentUrl.searchParams.delete("page");
        currentUrl.searchParams.set('size', selectedPageSize);
        window.location.replace(currentUrl.toString());
    });

    // 장바구니 버튼 클릭
    document.querySelectorAll('.cart-btn').forEach(button => {
        button.addEventListener('click', function () {
            // 수량 입력 필드를 찾아서 값을 가져옴
            const quantityInput = this.closest('.quantity-and-buttons').querySelector('.quantity-input');
            const quantity = parseInt(quantityInput.value);

            // 책 ID를 가져옴
            const bookId = this.closest('.border-bottom').querySelector('.book-checkbox').id.replace('bookCheckbox', '');

            // 책 정보를 가져옴
            const thumbnail = this.closest('.border-bottom').querySelector('.book-thumbnail').src;
            const title = this.closest('.border-bottom').querySelector('.book-title').textContent;
            const price = parseInt(this.closest('.border-bottom').querySelector('.book-cost').textContent.replace('원', ''));
            const discountPrice = parseInt(this.closest('.border-bottom').querySelector('.book-discount-cost').textContent.replace('원', ''));

            // 요청 데이터 생성
            const requestData = [
                {
                    bookId: parseInt(bookId),
                    thumbnail: thumbnail,
                    title: title,
                    price: price,
                    discountPrice: discountPrice,
                    quantity: quantity
                }
            ];

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
        });
    });
});
