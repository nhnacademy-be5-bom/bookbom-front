document.addEventListener("DOMContentLoaded", function () {
    // 체크박스
    const checkAllCheckbox = document.getElementById('checkAll');
    const itemCheckboxes = document.querySelectorAll('.custom-control-input');

    // 수량 감소 버튼 클릭
    document.querySelectorAll('.quantity-decrease').forEach(button => {
        button.addEventListener('click', function () {
            const itemID = this.getAttribute('data-item');
            const input = document.querySelector(`.quantity-input[data-item="${itemID}"]`);
            // 10진수 숫자로 변환
            let currentValue = parseInt(input.value, 10);
            let newValue = currentValue;
            if (isNaN(currentValue) || currentValue <= 1) {
                newValue = 1;
            } else {
                newValue -= 1;
            }
            input.value = newValue;
            if (newValue !== currentValue) { // 변경된 값과 이전 값이 다를 때만 업데이트
                updateQuantity(itemID, newValue, currentValue);
                updateItemTotal(this);
                updateCartSummaryIfChecked(this);
            }
        });
    });

    // 수량 증가 버튼 클릭
    document.querySelectorAll('.quantity-increase').forEach(button => {
        button.addEventListener('click', function () {
            const itemID = this.getAttribute('data-item');
            const input = document.querySelector(`.quantity-input[data-item="${itemID}"]`);
            let currentValue = parseInt(input.value, 10);
            let newValue = currentValue;
            if (isNaN(currentValue)) {
                newValue = 1;
            } else if (currentValue < 99) {
                newValue += 1;
            }
            input.value = newValue;
            if (newValue !== currentValue) { // 변경된 값과 이전 값이 다를 때만 업데이트
                updateItemTotal(this);
                updateCartSummaryIfChecked(this);
                updateQuantity(itemID, newValue, currentValue);
            }
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
            let id = this.getAttribute('data-item');
            let previousValue = parseInt(this.getAttribute('data-previous-value'), 10) || 1;
            let currentValue = parseInt(this.value, 10);
            if (isNaN(currentValue)) {
                currentValue = 1; // currentValue가 비어있거나 NaN인 경우 0으로 설정
            }
            this.value = currentValue;
            if (currentValue !== previousValue) { // 변경된 값과 이전 값이 다를 때만 업데이트
                updateItemTotal(this);
                updateCartSummaryIfChecked(this);
                updateQuantity(id, currentValue, previousValue);
            }
            // 이전 값 업데이트
            this.setAttribute('data-previous-value', currentValue);
        });
    });


    // 전체 선택 체크박스를 클릭했을 때
    checkAllCheckbox.addEventListener('change', function () {
        itemCheckboxes.forEach(function (checkbox) {
            checkbox.checked = checkAllCheckbox.checked;
        });
        updateCartSummary();
    });

    // 개별 상품 체크박스를 클릭했을 때의 동작
    itemCheckboxes.forEach(function (checkbox) {
        checkbox.addEventListener('change', function () {
            // 모든 개별 상품 체크박스가 선택되었으면 전체 선택 체크박스 체크
            checkAllCheckbox.checked = Array.from(itemCheckboxes).every(function (checkbox) {
                return checkbox.checked;
            });
            updateCartSummary();
        });
    });

    function updateCartSummaryIfChecked(element) {
        const itemRow = element.closest('tr');
        if (itemRow) {
            const itemCheckbox = itemRow.querySelector('.item-check');
            if (itemCheckbox && itemCheckbox.checked) {
                updateCartSummary();
            }
        }
    }

    function updateCartSummary() {
        let totalAmount = 0;
        let totalDiscount = 0;
        let shippingFee = 5000;

        itemCheckboxes.forEach(function (checkbox) {
            const itemRow = checkbox.closest('tr');
            if (itemRow) {
                const price = parseInt(itemRow.querySelector('.book-cost').textContent.replace('원', '').replace(/,/g, ''));
                const discountPrice = parseInt(itemRow.querySelector('.book-discount-cost').textContent.replace('원', '').replace(/,/g, ''));
                const quantity = parseInt(itemRow.querySelector('.quantity-input').value);

                if (checkbox.checked) {
                    totalAmount += price * quantity;
                    totalDiscount += discountPrice * quantity;
                }
            }
        });

        if (totalAmount >= 30000 || totalAmount === 0) {
            shippingFee = 0;
        }

        const paymentDue = totalAmount - (totalAmount - totalDiscount) + shippingFee;

        document.getElementById('totalAmount').textContent = numberWithCommas(totalAmount) + '원';
        document.getElementById('shippingFee').textContent = numberWithCommas(shippingFee) + '원';
        document.getElementById('productDiscount').textContent = numberWithCommas(totalAmount - totalDiscount) + '원';
        document.getElementById('paymentDue').textContent = numberWithCommas(paymentDue) + '원';
    }

    function updateItemTotal(element) {
        const itemRow = element.closest('tr');
        const discountPrice = parseInt(itemRow.querySelector('.book-discount-cost').textContent.replace('원', '').replace(/,/g, ''));
        const quantity = parseInt(itemRow.querySelector('.quantity-input').value);
        const itemTotal = itemRow.querySelector('#item-total');

        const total = discountPrice * quantity;
        itemTotal.textContent = numberWithCommas(total) + '원';
    }

    // 금액 표시
    function numberWithCommas(x) {
        return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }

    // 장바구니 상품 삭제
    document.querySelectorAll('.item-delete').forEach(button => {
        button.addEventListener('click', function () {
            const itemRow = this.closest('tr');
            const itemID = this.closest('tr').querySelector('.item-check').value;
            fetch(`/cart/items/${itemID}`, {
                method: 'DELETE',
            })
                .then(response => {
                    return response.json();
                })
                .then(data => {
                    if (data.header.resultMessage === "SUCCESS") {
                        itemRow.remove();
                    } else {
                        console.error("상품 삭제 실패: " + data.header.resultMessage);
                        alert("장바구니 상품 삭제에 실패하였습니다. 나중에 다시 시도해주세요.")
                    }
                })
                .catch(error => {
                    console.error("상품 삭제 실패: " + error);
                    alert("장바구니 상품 삭제에 실패하였습니다. 나중에 다시 시도해주세요.")
                })
        });
    });

    // 장바구니 수량 수정
    function updateQuantity(id, updateQuantity, previousQuantity) {
        fetch(`/cart/items/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({quantity: updateQuantity})
        })
            .then(response => {
                return response.json();
            })
            .then(data => {
                if (data.header.successful === false) {
                    console.error("수량 업데이트 실패: " + data.header.resultMessage);
                    alert("수량 변경에 실패하였습니다. 나중에 다시 시도해주세요.")
                    // 실패한 경우 이전 수량으로 복원
                    const input = document.querySelector(`.quantity-input[data-item="${id}"]`);
                    input.value = previousQuantity;
                }
            })
            .catch(error => {
                console.error("수량 업데이트 실패");
                alert("수량 변경에 실패하였습니다. 나중에 다시 시도해주세요.")
                // 실패한 경우 이전 수량으로 복원
                const input = document.querySelector(`.quantity-input[data-item="${id}"]`);
                input.value = previousQuantity;
            })
    }

    // 주문 폼 생성
    document.querySelector('.order-btn').addEventListener('click', function () {
        const checkedItems = document.querySelectorAll('.item-check:checked');

        if (checkedItems.length === 0) {
            alert("주문하실 상품을 선택해주세요.");
            return;
        }

        const form = document.createElement('form');
        form.method = 'post';
        form.action = '/order/wrapper';

        checkedItems.forEach((item, index) => {
            const bookId = item.value;
            const quantityInput = document.querySelector(`.quantity-input[data-item='${bookId}']`);
            const quantity = quantityInput.value;


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
        });
        document.body.appendChild(form);
        form.submit();
    });
});

