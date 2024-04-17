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
            console.log(keyword);
            let newUrl = window.location.pathname + '?keyword=' + keyword;
            console.log("path = " + window.location.pathname);
            console.log("newUrl = " + newUrl);
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
            currentUrl.searchParams.set('sorted', sortCondition);
            window.location.href = currentUrl.toString();
        });
    });
});
