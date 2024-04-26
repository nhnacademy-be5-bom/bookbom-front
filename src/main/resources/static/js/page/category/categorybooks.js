/* global bootstrap: false */
(() => {
    'use strict'
    const tooltipTriggerList = Array.from(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
    tooltipTriggerList.forEach(tooltipTriggerEl => {
        new bootstrap.Tooltip(tooltipTriggerEl)
    })
})()

document.addEventListener('DOMContentLoaded', function () {
    let queryParams = new URLSearchParams(window.location.search);

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
});
